package fr.sali.cantine.springsecurity;

import fr.sali.cantine.springsecurity.jwt.JwtTokenVerifier;
import fr.sali.cantine.springsecurity.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import fr.sali.cantine.springsecurity.springsecurityuser.CantineUserDailsService;
import jdk.jfr.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.net.http.HttpRequest;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtTokenVerifier jwtTokenVerifier;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    private CantineUserDailsService cantineUserDailsService ;
    public  SecurityConfig (CantineUserDailsService cantineUserDailsService , BCryptPasswordEncoder bCryptPasswordEncoder,  JwtTokenVerifier jwtTokenVerifier){
        this.cantineUserDailsService =  cantineUserDailsService ;
        this.bCryptPasswordEncoder =  bCryptPasswordEncoder;
        this.jwtTokenVerifier   =  jwtTokenVerifier;
    }
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

     return  http.csrf(csrf->csrf.disable())
             .cors(Customizer.withDefaults())
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
             .authorizeRequests(
                     authorized->{
                         authorized.antMatchers("/cantine/user/signUP", "/cantine/upload").permitAll();
                         authorized.antMatchers("/cantine/user/existemail","/cantine/users/activatedAcount/**" , "/cantine/user/confirm-acount/**").permitAll();
                         authorized.antMatchers("/cantine/download/**").permitAll();
                         authorized.antMatchers("/cantine/meals",  "cantine/meals/getOne/**").permitAll();
                         authorized.antMatchers("/cantine/menus/getMenus").permitAll();
                         authorized.anyRequest().authenticated();
                     }
             )
             .authenticationProvider(authenticationProvider())
             .addFilter( new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
             .addFilterBefore(jwtTokenVerifier, JwtUsernameAndPasswordAuthenticationFilter.class)
             .build();
   }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization" , "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
   @Bean
   public AuthenticationManager authenticationManager (){
        return new ProviderManager(authenticationProvider());
   }

   public AuthenticationProvider  authenticationProvider (){
       DaoAuthenticationProvider provider =  new DaoAuthenticationProvider();
       provider.setUserDetailsService(cantineUserDailsService);
       provider.setPasswordEncoder(bCryptPasswordEncoder);
       return provider;
   }


}
