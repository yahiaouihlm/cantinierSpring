package fr.sali.cantine.springsecurity;


import fr.sali.cantine.springsecurity.jwt.JwtTokenVerifier;
import fr.sali.cantine.springsecurity.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import fr.sali.cantine.springsecurity.springsecurityuser.CantineUserDailsService;
import fr.sali.cantine.springsecurity.springsecurityuser.CantineUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
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
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
             .authorizeRequests(
                     authorized->{
                         authorized.antMatchers("/cantine/user/signUP").permitAll();
                         authorized.anyRequest().authenticated();

                     }
             )
             .authenticationProvider(authenticationProvider())
             .addFilter( new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
             .addFilterBefore(jwtTokenVerifier, JwtUsernameAndPasswordAuthenticationFilter.class)
             .build();
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
