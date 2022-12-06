package fr.sali.cantine.spring;

import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.service.AuthentificationService;
import fr.sali.cantine.spring.security.filters.AuthentificationFilter;
import fr.sali.cantine.spring.security.filters.JwtAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends   AbstractSpringSecurityConfiguration {
    @Override
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return super.authenticationManager(authenticationConfiguration);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ,  AuthenticationManager authenticationManager) throws Exception {

        return http.
                authorizeRequests()
                .antMatchers("/login").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().and()
                .addFilterBefore(new AuthentificationFilter (authenticationManager, this.env),
                UsernamePasswordAuthenticationFilter.class)

                .build();
    }
    @Bean
    public  UserDetailsService userDetailsService (){
        return  new InMemoryUserDetailsManager(
                 User.withUsername("user" ).password (passwordEncoded().encode("password")).roles("ADMIN").build()
        );
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoded(){
        return  new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
    }


}
