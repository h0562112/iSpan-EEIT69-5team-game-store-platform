package com.ispan.eeit69.controller.membercontroller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

	@GetMapping("/downloadGame")
	public ResponseEntity<byte[]> downloadGame() {
		// 以 InputStream 讀取，打包成 war / jar 後 classpath 內的檔案也能下載
		try (InputStream in = new ClassPathResource("static/game_Libary/ball.jar").getInputStream()) {
			byte[] fileContent = in.readAllBytes();

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ball.jar");

			return ResponseEntity.ok().headers(headers).body(fileContent);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		}
	}
}
