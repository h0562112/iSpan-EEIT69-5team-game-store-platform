# ---- build ----
FROM eclipse-temurin:11-jdk AS build
WORKDIR /app

# 先複製 pom 與 wrapper，利用 layer cache 加速相依套件下載
COPY .mvn .mvn
COPY mvnw pom.xml ./
# Windows 的 CRLF 換行會讓 mvnw 在 Linux 無法執行，先轉成 LF
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw && ./mvnw -B -q dependency:go-offline

COPY src src
RUN ./mvnw -B -q package -DskipTests

# ---- run ----
FROM eclipse-temurin:11-jre
WORKDIR /app

# 專案使用 JSP，必須以 war 方式執行 (executable war，內嵌 Tomcat)
COPY --from=build /app/target/springdemo-*.war app.war

COPY .env .env

EXPOSE 8080
# DB_URL / DB_PASSWORD 等設定請用環境變數或 docker-compose 的 env_file 傳入
ENTRYPOINT ["java", "-jar", "/app/app.war"]
