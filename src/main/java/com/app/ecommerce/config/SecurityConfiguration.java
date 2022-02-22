package com.app.ecommerce.config;

import com.app.ecommerce.model.CartItemModel;
import com.app.ecommerce.model.CartModel;
import com.app.ecommerce.security.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApplicationProperties properties;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .addFilterAfter(new JwtTokenFilter(properties), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user/signup").permitAll()
                //A partir de acá no iría, pero no tengo otra opción porque no logro hacer andar el jwt.

                //ProductController
                .antMatchers(HttpMethod.GET, "/products/all").permitAll()
                .antMatchers(HttpMethod.POST, "/products/product").permitAll()
                .antMatchers(HttpMethod.GET, "/products").permitAll()
                .antMatchers(HttpMethod.PUT, "/products/save").permitAll()
                .antMatchers(HttpMethod.DELETE, "/products/delete").permitAll()

                //CartController
                .antMatchers(HttpMethod.GET, "/cart/get").permitAll()
                .antMatchers(HttpMethod.GET, "/cart/buy").permitAll()
                .antMatchers(HttpMethod.PUT, "/cart/add").permitAll()
                .antMatchers(HttpMethod.PUT, "/cart/increase").permitAll()
                .antMatchers(HttpMethod.PUT, "/cart/decrease").permitAll()
                .antMatchers(HttpMethod.DELETE, "/cart/delete").permitAll()
                .antMatchers(HttpMethod.DELETE, "/cart").permitAll()

                .anyRequest().authenticated();
    }

}