package com.ispan.eeit69.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.ItemCategory;
import com.ispan.eeit69.repository.ItemCategoryRepository;
import com.ispan.eeit69.repository.ItemsRepository;

/** 給搜尋引擎看的 robots.txt 與 sitemap.xml（sitemap 從資料庫動態產生，商品增減會自動更新） */
@RestController
public class SeoController {

	/** 網站正式網址，例如 https://www.example.com。沒設定時用目前請求的網址 */
	@Value("${app.base-url:}")
	private String configuredBaseUrl;

	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private ItemCategoryRepository categoryRepository;

	private String baseUrl(HttpServletRequest request) {
		String base = configuredBaseUrl == null ? "" : configuredBaseUrl.trim();
		if (base.isEmpty()) {
			base = ServletUriComponentsBuilder.fromContextPath(request).build().toUriString();
		}
		return base.endsWith("/") ? base.substring(0, base.length() - 1) : base;
	}

	@GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
	public String robots(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("User-agent: *\n");
		sb.append("Allow: /\n");
		// 後台與會員私人頁面不需要被搜尋到（真正的保護仍然靠登入權限）
		String[] blocked = { "/adminpages/", "/cart", "/api/", "/orders", "/coupon", "/findEmployee.do",
				"/findfriendsid", "/newFriends", "/findPlayergameid", "/findByfriendmessage", "/friend/", "/websocket/",
				"/login", "/register", "/forgotPassword", "/logout" };
		for (String path : blocked) {
			sb.append("Disallow: ").append(path).append('\n');
		}
		sb.append('\n').append("Sitemap: ").append(baseUrl(request)).append("/sitemap.xml\n");
		return sb.toString();
	}

	@GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8")
	public String sitemap(HttpServletRequest request) {
		String base = baseUrl(request);
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");

		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");

		appendUrl(xml, base + "/", null, "daily", "1.0");
		appendUrl(xml, base + "/news", null, "weekly", "0.6");

		for (ItemCategory category : categoryRepository.findAll()) {
			if (category.getCategoryName() != null) {
				appendUrl(xml, base + "/ItemCategory?category=" + encode(category.getCategoryName()), null, "weekly",
						"0.8");
			}
		}

		List<Item> items = itemsRepository.findAll();
		for (Item item : items) {
			String lastmod = item.getLastTime() == null ? null : day.format(item.getLastTime());
			appendUrl(xml, base + "/Item/" + item.getId(), lastmod, "weekly", "0.7");
		}

		xml.append("</urlset>\n");
		return xml.toString();
	}

	private void appendUrl(StringBuilder xml, String loc, String lastmod, String changefreq, String priority) {
		xml.append("  <url>\n");
		xml.append("    <loc>").append(escapeXml(loc)).append("</loc>\n");
		if (lastmod != null) {
			xml.append("    <lastmod>").append(lastmod).append("</lastmod>\n");
		}
		xml.append("    <changefreq>").append(changefreq).append("</changefreq>\n");
		xml.append("    <priority>").append(priority).append("</priority>\n");
		xml.append("  </url>\n");
	}

	private String encode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	private String escapeXml(String value) {
		return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
				.replace("'", "&apos;");
	}
}
