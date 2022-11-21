package fr.sali.cantine.spring.security;

import fr.sali.cantine.entity.ImageEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.
                authorizeRequests()
                .antMatchers("/private").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
              //  .oauth2Login()
               //    .and()
                .build();
    }
    @Bean
    public  UserDetailsService userDetailsService (){
        return  new InMemoryUserDetailsManager(
                 User.withUsername("user" ).password("password").roles("ADMIN").build()
        );
    }


}
