package com.ispan.eeit69.controller.membercontroller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class DownloadController {

    private final ResourceLoader resourceLoader;

    public DownloadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/downloadGame")
    public ResponseEntity<byte[]> downloadGame() {
        try {
            // 使用 ResourceLoader 加載 .jar 文件
            Resource resource = resourceLoader.getResource("classpath:static/game_Libary/ball.jar");
            
            // 讀取 .jar 文件的內容
            byte[] fileContent = Files.readAllBytes(resource.getFile().toPath());

            // 創建 HTTP 響應頭，指定文件名和內容類型
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ball.jar");

            // 返回響應實體，包含文件內容和響應頭
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);

        } catch (IOException e) {
            // 處理異常（例如，文件不存在）
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
