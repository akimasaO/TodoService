package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		// 認可の設定
		http.authorizeRequests()
        		.antMatchers("/prelogin","/register") 
        			.permitAll() // ログインAPI、会員登録APIは認証に関係なく許可する
        		.anyRequest()
        			.authenticated() // 他のAPIは認証されたユーザのみ許可する
        
        // 認証、認可の例外処理
        .and()		
        	.exceptionHandling()
        		.authenticationEntryPoint(authenticationEntryPoint())
            	.accessDeniedHandler(accessDeniedHandler())

        // 認証と成功・失敗時の処理	
        .and()
        	.formLogin()
        		.loginProcessingUrl("/login").permitAll() // ログイン処理をするURL
		        	.usernameParameter("name")
		        	.passwordParameter("password")
	        	.successHandler(authenticationSuccessHandler())
	            .failureHandler(authenticationFailureHandler())
        	
		//　ログアウト時の処理
        .and()
        	.logout()
        		.logoutUrl("/logout")
        		.invalidateHttpSession(true)
        		.deleteCookies("JSESSIONID")
        		.logoutSuccessHandler(logoutSuccessHandler())
        	

        // CSRF対策機能設定
        //　GETは特に問題なく200 OKを返しているが、POST/PATCHを投げると403 Forbiddenが返ってきていた問題。
		.and()
			.csrf()
//				.ignoringAntMatchers("/login")
//				.csrfTokenRepository(new CookieCsrfTokenRepository())
				.disable() // CSRF対策機能を無効化する
		;

        
    }

	// 認証処理のコンフィグレーション
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth,
								@Qualifier("simpleUserDetailsService") UserDetailsService userDetailsService
								) throws Exception{
		auth.eraseCredentials(true)
			.userDetailsService(userDetailsService);
	}

	//パスワードをハッシュ化
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	AuthenticationEntryPoint authenticationEntryPoint() {
        return new SimpleAuthenticationEntryPoint();
    }

    AccessDeniedHandler accessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }
	
	// ログイン成功時にステータス200を返すハンドラーを生成
	AuthenticationSuccessHandler authenticationSuccessHandler() {
	    return new SimpleAuthenticationSuccessHandler();
	}
	
	AuthenticationFailureHandler authenticationFailureHandler() {
	    return new SimpleAuthenticationFailureHandler();
	}
	
	LogoutSuccessHandler logoutSuccessHandler() {
	  return new HttpStatusReturningLogoutSuccessHandler();
	}
}
