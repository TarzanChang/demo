package com.example_tarzan.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//向spring boot標示該類別為設定檔，Spring將會在啟動時讀取
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                //stateless jwt 用不上 csrf
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
//                                .anyRequest().permitAll()  //全部請求都不需驗證身分
                                .requestMatchers(HttpMethod.POST,"/jwt/register/**").hasRole("ADMIN")
                                .requestMatchers("/jwt/**").permitAll() //代表 jwt所有都開放不控管
                                .requestMatchers(HttpMethod.GET,"/session/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/session/register/**").hasRole("ADMIN")
                                .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html").permitAll()
                                .requestMatchers(HttpMethod.GET,"/projects/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/v2/users/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/v2/users/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.POST,"/session/register/**").permitAll()
//                                // Spring Security 將會自動加上前綴 ROLE_ -> ROLE_ADMIN
                                .requestMatchers("/products/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/suppliers/**").permitAll()
                                .requestMatchers("/suppliers/**").hasRole("SUPPLIER")
                                .anyRequest().authenticated()
                        //可以有邏輯處理，但建議依照需求單純化程式碼
//                                .requestMatchers(request -> {
//                                    //timer 10:00~20:00
//                                    if(timer ...){
//                                        return request.getRequestURL().equals()
//                                    }
//                                },"/jwt/**").permitAll()
                )
                // restful 核心： 伺服器無法從 session 中獲得使用者資訊
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 確保 spring security 進行 usernamePassword的驗證以前，程式的 jwtAuthFilter 會先被執行
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
