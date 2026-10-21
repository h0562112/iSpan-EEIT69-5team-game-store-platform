package com.ispan.eeit69.config;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import com.ispan.eeit69.model.Role;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.RoleRepository;
import com.ispan.eeit69.service.UserService;
import com.ispan.eeit69.service.member.AvatarService;

@Configuration
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;
	@Autowired
	private AvatarService avatarService;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, BCryptPasswordEncoder passwordEncoder)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
		FilterRegistrationBean<HiddenHttpMethodFilter> filterRegBean = new FilterRegistrationBean<>();
		filterRegBean.setFilter(new HiddenHttpMethodFilter());
		return filterRegBean;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer
				.antMatchers(HttpMethod.DELETE, "/adminpages/employee/EmployeeDelete/**").hasRole("ADMIN")
				.antMatchers("/save", "/", "/register", "/css/**", "/js/**", "/images/**", "/bootstrap-3.4.1/**",
						"/registersucessful", "/logout", "/forgotPassword", "/websocket/**", "/search",
						"/access-denied", "/error", "/api/addToCart", "/robots.txt", "/sitemap.xml")
				.permitAll()
				// 訪客可以瀏覽商品、分類與評論
				.antMatchers(HttpMethod.GET, "/Item/**", "/ItemCategory", "/api/reviews", "/news").permitAll()
				.antMatchers("/adminpages/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/adminpages/employee/editEmployee/**").hasRole("ADMIN")

				.anyRequest().hasRole("USER"))
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error=true")
						.successHandler(loginSuccessHandler()).permitAll())
				.oauth2Login(oauth2 -> oauth2.loginPage("/login").defaultSuccessUrl("/", true)
						.successHandler((request, response, authentication) -> {
							DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
							String username = oauthUser.getName();
							String email = oauthUser.getAttribute("email");

							User user = userService.findByUserName(username);
							if (user == null) {
								user = new User(username, email, "GoogleOAuth2");
								Role userRole = roleRepository.findByName("USER").orElseGet(() -> {
									Role newRole = new Role();
									newRole.setName("USER");
									roleRepository.save(newRole);
									return newRole;
								});
								user.setRoles(new HashSet<>(Arrays.asList(userRole)));
								userService.save(user);
							}
							populateSession(request, username);
							new SavedRequestAwareAuthenticationSuccessHandler().onAuthenticationSuccess(request,
									response, authentication);
						}).failureHandler((request, response, exception) -> {
							response.sendRedirect("/login?error=true");
						}))
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
						.logoutSuccessUrl("/").invalidateHttpSession(true).clearAuthentication(true)
						.deleteCookies("JSESSIONID").permitAll())
				.exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

		return http.build();
	}

	/** 把登入者與頭像放進 session，供各頁面的導覽列與會員功能使用 (請勿刪除) */
	private void populateSession(HttpServletRequest request, String username) {
		HttpSession session = request.getSession();

		com.ispan.eeit69.model.User myUser = userService.findByUserName(username);
		session.setAttribute("myUser", myUser);
		if (myUser == null) {
			return;
		}

		com.ispan.eeit69.model.Avatar myimage = avatarService.findAvatarByUserId(myUser.getId());
		if (myimage != null && myimage.getPicture() != null) {
			try {
				// Clob 內容本身就是 Base64 字串，直接取出
				Clob itemPictureClob = myimage.getPicture();
				session.setAttribute("itemPictureBase64",
						itemPictureClob.getSubString(1, (int) itemPictureClob.length()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new SimpleUrlAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				populateSession(request, authentication.getName());

				// session 寫完後再重導向
				if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
					getRedirectStrategy().sendRedirect(request, response, "/adminpages/adminindex");
				} else {
					super.onAuthenticationSuccess(request, response, authentication);
				}
			}
		};
	}
}
