package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		// 認可の設定
		http.authorizeRequests()
        		.antMatchers("/login","/register") 
        			.permitAll() // ログインAPI、会員登録APIは認証に関係なく許可する
        		.anyRequest()
        			.authenticated() // 他のAPIは認証されたユーザのみ許可する
        
        // 認証、認可の例外処理
        .and()		
        	.exceptionHandling()

        // 認証と成功・失敗時の処理	
        .and()
        	.formLogin()
        		.loginProcessingUrl("/login").permitAll() // ログイン処理をするURL
		        	.usernameParameter("name")
		        	.passwordParameter("password")
        		//.successHandler(authenticationSuccessHandler())
		        //.failureHandler(authenticationSuccessHandler())
        	
		//　ログアウト時の処理
        .and()
        	.logout()
        		.logoutUrl("/logout")
        		.invalidateHttpSession(true)
        		.deleteCookies("JSESSIONID")
        		//.logoutsuccessHandler(logoutSuccessHandler())
        	

        // CSRF対策機能設定
        //　GETは特に問題なく200 OKを返しているが、POST/PATCHを投げると403 Forbiddenが返ってきていた問題。
		.and()
//			.csrf()
//				.ignoringAntMatchers("/login")
//				.csrfTokenRepository(new csrfTokenRepository())
//				.disable(); 
			.csrf()
        		.disable(); // CSRF対策機能を無効化する
        
    }

//	// 認証処理のコンフィグレーション
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth,
//								@Qualifier("simpleUserDetailsService") UserDetailsService userDetailsService,
//								PasswordEncoder passwordEncoder) throws Exception{
//		auth.eraseCredentials(true)
//			.userDetailsService(userDetailsService)
//			.passwordEncoder(passwordEncoder);
//	}

	//パスワードをハッシュ化
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
