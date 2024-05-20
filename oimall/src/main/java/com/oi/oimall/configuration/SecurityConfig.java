package com.oi.oimall.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

//import com.example.yongDiary.configuration.filter.CustomAuthenticationSuccessHandler;
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    //Á¢±Ù ±ÇÇÑ ¾ø´Â °æ¿ì ¿¹¿Ü Ã³¸® ÇÒ Å¬·¡½º 
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//    	//아래는 AccessDeniedHandler 상속받아서 직접 생성
//		System.out.println("accessDeniedHandler start...");
//
//        return new CustomAccessDeniedHandler();
//    }
    
    // ÇØ½¬ ¾ÏÈ£È­ ¹æ½ÄÀ» »ç¿ëÇÏ°Ú´Ù. -> password ¾ÏÈ£È­
    //@Bean »ç¿ëÇÏ¸é ÇØ´ç ¸Þ¼­µåÀÇ ¸®ÅÏµÇ´Â ¿ÀºêÁ§Æ®¸¦ IoC·Î µî·ÏÇØÁÜ
    @Bean
    BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

    //¼¼¼Ç °ü¸® ¹× ÀÎÁõ ½ÇÆÐ ÇÚµé¸µ
    @Bean
    SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
		//SimpleUrlAuthenticationFailureHandler 객체를 생성하여 반환
		System.out.println("authenticationFailureHandler start...");
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
	    failureHandler.setUseForward(true);
	    failureHandler.setDefaultFailureUrl("/user/loginPage?error=true");
	    
	    return failureHandler;
	}
	 
	// 인증요청으로 들어온 토큰이 올바른 유저인지 인증 수행하므로 빈 등록 필수!
	@Bean
	AuthenticationManager authenticationManager(
		AuthenticationConfiguration authenticationConfiguration
	) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}


//	WebSecurityConfigurerAdapter는  Deprecated 되었으므로 사용하지 않고 아래와 같이 SecurityFilterChain을 Bean으로 등록하여 사용 
    @SuppressWarnings("removal")
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
		System.out.println("filterChain start...");
		//접근 권한 없는 경우 예외처리할 핸들러 필터체인에 등록
//		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		
		http.authorizeHttpRequests((requests) -> requests
				//구독서비스
//				.antMatchers("/chating").permitAll()
//				.antMatchers("/mapView").hasAnyRole("ADMIN", "USER")
//				.antMatchers("/map/myMapList").hasAnyRole("ADMIN", "USER")
//				.antMatchers("/chating").authenticated()
				.anyRequest().permitAll()

			);		
		
		http.formLogin((form) -> form
//				.loginPage("/page/Login.vue")
				.permitAll()
//				.usernameParameter("user_id")		// login에 필요한 id값 설정 (default는 username)
//                .passwordParameter("user_pw")	// login에 필요한 password 값  (default password)
//                .loginProcessingUrl("/api/account/login")	// login주소가 호출 되면 시큐리티가 낚아채서 대신 로그인 진행해줌
//                .failureUrl("/user/loginPage?error=true")
//                .successHandler(successHandler())
			);
		
		// Logout 설정.
		http.logout((logout) -> logout
				.permitAll()
				.logoutSuccessUrl("/api/account/login")
				.invalidateHttpSession(true) //세션 무효화 -현재 세션을 끝내고 새로운 세션을 시작
			);

        // ¼¼¼Ç ¼³Á¤ Ãß°¡
        http.sessionManagement(management -> management							//¼¼¼Ç °ü¸® ¼³Á¤À» ½ÃÀÛ
                .maximumSessions(2)								//µ¿½Ã¿¡ Çã¿ëµÇ´Â ÃÖ´ë ¼¼¼Ç ¼ö
                .expiredUrl("/info/loginForm?expired=true")		//¼¼¼ÇÀÌ ¸¸·áµÈ °æ¿ì ¸®µð·º¼ÇÇÒ URL(·Î±×ÀÎ ÆäÀÌÁö·Î ÀÌµ¿)
                .and()
                .sessionAuthenticationFailureHandler(authenticationFailureHandler())); //¼¼¼Ç¿¡¼­ÀÇ ÀÎÁõ ½ÇÆÐ ½Ã Ã³¸®¸¦ ´ã´çÇÏ´Â ÇÚµé·¯¸¦

		return http.build();
	}
}