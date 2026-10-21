# 5team 遊戲電商平台

5team 是一個以 Spring Boot 開發的遊戲電商網站，除了商品瀏覽、購物車與評論之外，還包含會員系統、好友系統與即時聊天，並提供後台管理介面。

## 功能

### 商城（前台）
- 首頁、遊戲分類瀏覽、商品詳情頁
- 商品關鍵字搜尋
- 購物車（加入購物車 API）
- 商品評論：新增、修改、刪除
- 遊戲下載

### 會員與好友
- 註冊（註冊成功會寄送確認信）、登入、登出
- Google 帳號登入（OAuth2）
- 忘記密碼（寄送重設密碼信）
- 個人資料檢視與編輯、大頭貼上傳
- 遊戲庫
- 好友列表、新增與刪除好友、好友邀請
- 好友即時聊天（WebSocket）

### 後台管理（`ADMIN` 角色）
- 使用者：查詢、關鍵字搜尋、刪除
- 員工：新增、查詢、關鍵字搜尋、修改、刪除
- 商品：新增、查詢、關鍵字搜尋、修改、刪除

## 技術

| 類別 | 使用技術 |
| --- | --- |
| 語言 / 框架 | Java 11、Spring Boot 2.7.14、Spring MVC、Spring Data JPA (Hibernate) |
| 安全 | Spring Security（表單登入、角色 `USER` / `ADMIN`）、OAuth2 Client（Google）、BCrypt |
| 即時通訊 | Spring WebSocket |
| 視圖 | JSP + JSTL（以 war 封裝，內嵌 Tomcat + Jasper） |
| 資料庫 | MySQL 8 |
| 其他 | Spring Mail（Gmail SMTP）、Druid、fastjson、Docker |

## 專案結構

```
.
├── pom.xml
├── Dockerfile / docker-compose.yml
├── schema.sql                  # 資料表結構（由 JPA Entity 反推）
├── seed.sql                    # 測試用假資料
├── .env.example                # 環境變數範本
└── src/main
    ├── java/com/ispan/eeit69
    │   ├── config/             # Spring Security、Bean 設定
    │   ├── controller/         # 前台與商品 / 購物車 Controller
    │   │   ├── adminpage/      # 後台管理 Controller
    │   │   └── membercontroller/   # 會員、好友、下載
    │   ├── service/            # 商業邏輯
    │   ├── repository/ dao/   # 資料存取
    │   ├── model/              # JPA Entity
    │   ├── validator/          # 表單驗證
    │   └── utils/memberutils/  # WebSocket 聊天
    ├── resources/              # application.properties、靜態資源 (css / js / images)
    └── webapp/WEB-INF/views/   # JSP 頁面
```

## 資料表

`user`、`role`、`users_roles`、`avatar`、`employees`、`itemCategory`、`items`、`cart`、`review`、`friends`、`friendsMessages`、`player_Games_library`

## 快速開始

### 需求
- JDK 11
- MySQL 8（或使用 Docker）
- 不需要另外安裝 Maven，專案內附 Maven Wrapper（`mvnw`）

### 1. 建立資料庫

```sql
-- 建立資料庫與資料表
source schema.sql;
-- 選用：匯入測試資料（請只執行一次）
source seed.sql;
```

也可以只建立空的 `5team` 資料庫，應用程式第一次啟動時會由 JPA（`ddl-auto=update`）自動建表。

### 2. 設定環境變數

將 `.env.example` 複製成 `.env`，放在專案根目錄並填入自己的值（`.env` 已加入 `.gitignore`，不會進版控）。也可以直接使用同名的系統環境變數。

| 變數 | 說明 | 預設 |
| --- | --- | --- |
| `DB_URL` | 資料庫連線字串 | `jdbc:mysql://localhost:3306/5team` |
| `DB_USERNAME` | 資料庫帳號 | `root` |
| `DB_PASSWORD` | 資料庫密碼 | 無（必填） |
| `GOOGLE_CLIENT_ID` | Google OAuth2 Client ID | 無（Google 登入需要） |
| `GOOGLE_CLIENT_SECRET` | Google OAuth2 Client Secret | 無（Google 登入需要） |
| `MAIL_USERNAME` | 寄信用的 Gmail 帳號 | 無（註冊 / 忘記密碼寄信需要） |
| `MAIL_PASSWORD` | Gmail 應用程式密碼（需開啟兩步驟驗證） | 無（註冊 / 忘記密碼寄信需要） |

### 3. 啟動

```bash
./mvnw spring-boot:run
```

Windows 可使用 `mvnw.cmd spring-boot:run`。啟動後開啟 <http://localhost:8080>。

### 使用 Docker

```bash
docker compose up --build
```

`docker-compose.yml` 只啟動應用程式，並讀取根目錄的 `.env`。資料庫需另外準備，`DB_URL` 請指向容器可連到的 MySQL（容器內的 `localhost` 不是你的主機）。

### 打包

```bash
./mvnw package -DskipTests
java -jar target/springdemo-0.0.1-SNAPSHOT.war
```

專案使用 JSP，必須以 war 方式執行。

## 測試帳號

匯入 `seed.sql` 後可使用下列帳號，**密碼皆為 `Test1234`**：

| 帳號 | 角色 |
| --- | --- |
| `demo_alice`、`demo_bob`、`demo_carol` | 一般會員 |
| `demo_admin` | 管理員（登入後導向後台） |

## 權限規則

- 公開：首頁、登入、註冊、忘記密碼、靜態資源
- `USER`：其餘頁面（會員中心、好友、購物車等）
- `ADMIN`：`/adminpages/**` 後台

## 團隊

iSpan EEIT69 第五組
