package com.dieguidev.spring_security.config.security;

import com.dieguidev.spring_security.config.security.filter.JwtAuthenticationFilter;
import com.dieguidev.spring_security.persistence.utils.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SecurityFilterChain filterChain = http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
//                esta linea es para agregar un filtro antes
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authReqConfig -> {

//                    Autorizacion de endpoints e productos
//                    authReqConfig.requestMatchers(HttpMethod.GET, "/products")
//                                    .hasAuthority(RolePermission.READ_ALL_PRODUCTS.name());
//
//                    authReqConfig.requestMatchers(HttpMethod.GET, "/products/{productId}")
//                            .hasAuthority(RolePermission.READ_ONE_PRODUCT.name());
//
//                    authReqConfig.requestMatchers(HttpMethod.POST, "/products")
//                            .hasAuthority(RolePermission.CREATE_ONE_PRODUCT.name());
//
//                    authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}")
//                            .hasAuthority(RolePermission.UPDATE_ONE_PRODUCT.name());
//
//                    authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/disabled")
//                            .hasAuthority(RolePermission.DISABLE_ONE_PRODUCT.name());
//
////                    Autorizacion de endpoints de categorias
//                    authReqConfig.requestMatchers(HttpMethod.GET, "/ca")
//                            .hasAuthority(RolePermission.READ_ALL_PRODUCTS.name());
//
//                    authReqConfig.requestMatchers(HttpMethod.GET, "/products/{productId}")
//                            .hasAuthority(RolePermission.READ_ONE_PRODUCT.name());
//
//                    authReqConfig.requestMatchers(HttpMethod.POST, "/products")
//                            .hasAuthority(RolePermission.CREATE_ONE_PRODUCT.name());
//
//                    authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}")
//                            .hasAuthority(RolePermission.UPDATE_ONE_PRODUCT.name());
//
//                    authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/disabled")
//                            .hasAuthority(RolePermission.DISABLE_ONE_PRODUCT.name());



                    authReqConfig.requestMatchers(HttpMethod.POST,"/customers").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

                    authReqConfig.anyRequest().authenticated();

                })
                .build();
        return filterChain;
    }

}
