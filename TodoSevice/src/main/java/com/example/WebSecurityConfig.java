package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        //アプリケーションにアクセスするときはログインページを通ることになる
        //ログインページへのアクセスは全員許可する。
        http.formLogin()
            .loginPage("/delete")
            .permitAll();
        
//        //会員登録機能へのアクセスは全て許可する
//        http.authorizeRequests()
//            .antMatchers("/register").permitAll()
//            .anyRequest().authenticated();

        // 全てのリクエストに対して認証をかける -> 認証されない場合ステータス403が返却される
        http.authorizeRequests()
            .anyRequest().authenticated();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
