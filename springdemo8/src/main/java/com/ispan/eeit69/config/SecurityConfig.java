package com.ispan.eeit69.config;

import java.io.IOException;
import java.io.Reader;
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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("http://10.0.101.88:8080","http://10.0.101.72:8080")
	                    .allowedMethods("GET", "POST", "PUT", "DELETE")
	                    .allowCredentials(true);
	        }
	    };
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
				.antMatchers("/save", "/", "/register", "/css/**", "/js/**", "/images/**", "/registersucessful",
						"/logout", "/forgotPassword", "/websocket/**", "/friend/save", "/findByfriendmessage", "/api",
						"/search")
				.permitAll().antMatchers("/adminpages/**").hasRole("ADMIN")
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

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new SimpleUrlAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
					getRedirectStrategy().sendRedirect(request, response, "/adminpages/adminindex");
				} else {
					super.onAuthenticationSuccess(request, response, authentication);
				}

				// 會員與好友系統抓使用者ip請勿刪除
				HttpSession session = request.getSession();

				com.ispan.eeit69.model.User myUser = userService.findByUserName(authentication.getName());
				session.setAttribute("myUser", myUser);
				
	System.out.println("給我內容"+myUser.getId());
				
//				com.ispan.eeit69.model.User myUsers = userService.findByUserName(authentication.getName());

				if (myUser != null) {
					Integer userId = myUser.getId();
					System.out.println("給我內容"+userId);
								
		            com.ispan.eeit69.model.Avatar myimage = avatarService.findAvatarByUserId(userId);
		            
		            if (myimage != null) {
		            System.out.println("Base64 編碼後的字串： " + myimage);
		            // 轉換Clob為Base64
		          
		            try {
			            Clob itemPictureClob = myimage.getPicture();
			            // 直接從 Clob 讀取字符數據，並轉換為 String
			            Reader reader = itemPictureClob.getCharacterStream();
			            char[] buffer = new char[(int) itemPictureClob.length()];
			            reader.read(buffer);
			            String itemPictureBase64 = new String(buffer);  // 這裡，我們直接將讀取的字符數據用作 Base64 編碼的字符串
	
			            // 將 Base64 編碼的圖片數據添加到 Model 中
			            session.setAttribute("itemPictureBase64",itemPictureBase64);
			            System.out.println("Base64 編碼後的字串： " + itemPictureBase64);
			        } catch (SQLException | IOException e) {
			            // 處理異常
			            e.printStackTrace();
			        }	            

				}
				}
				
			}
		};
	}
}
