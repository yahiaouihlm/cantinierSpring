package fr.sali.cantine.springsecurity.jwt;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sali.cantine.dto.in.LoginDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUsernameAndPasswordAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
    private  static final Logger LOG = LogManager.getLogger();
     private AuthenticationManager authenticationManager ;
     public  JwtUsernameAndPasswordAuthenticationFilter (AuthenticationManager authenticationManager){
         this.authenticationManager= authenticationManager ;
     }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var  username =  request.getParameter("username");
        var passsword  = request.getParameter("password");
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(passsword) ) {
            JwtUsernameAndPasswordAuthenticationFilter.LOG
                    .debug("--> JwtAuthenticationFilter.attemptAuthentication(email, password) as Json in Body");
            String body  = null ;
            try {
                body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                var mapper = new ObjectMapper();
                var login  = mapper.readValue(body ,  LoginDto.class);
                username =  login.getEmail();
                passsword =  login.getPassword() ;
            } catch (IOException lExp) {
                JwtUsernameAndPasswordAuthenticationFilter.LOG.error(
                        "--> JwtAuthenticationFilter.attemptAuthentication - Error, your JSon is not right!, found {}, should be something like {\"email\":\"toto@gmail.com\",\"password\":\"bonjour\"}. DO NOT use simple quote!",
                        body, lExp);
            }

        } else {
            JwtUsernameAndPasswordAuthenticationFilter.LOG
                    .debug("--> JwtAuthenticationFilter.attemptAuthentication(email, password) as parameter");

        }
        JwtUsernameAndPasswordAuthenticationFilter.LOG.debug("--> JwtAuthenticationFilter.attemptAuthentication({}, [PROTECTED])",
                username);

        try {
            Authentication  authentication =  new UsernamePasswordAuthenticationToken(username , passsword );
             ///

            return this.authenticationManager.authenticate(authentication) ;
        }catch (  Exception  e ){
            Map<String, String> idToken = new HashMap<>();

            response.setContentType("application/json");
            idToken.put("status" , HttpStatus.FORBIDDEN.name() );
            idToken.put("message" ,  "Authentication error");
            try {
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return null ;
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String  key  =  "secret key"  ;
        Algorithm algorithm =  Algorithm.HMAC256(key.getBytes());

        String jwtAccessToken  = JWT.create()
                .withSubject(authResult.getName())   //  600=> 60 (in first of application)     ('+5 * 6000 *1000')
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 6000 *1000))
                .withIssuer(request.getRequestURI())
                .withClaim("roles" , authResult.getAuthorities().stream().map(GrantedAuthority:: getAuthority).collect(Collectors.toList()))
                .sign(algorithm);


        var  context  =  SecurityContextHolder.getContext() ;
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        /*String refreshToken  =  JWT .create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);*/

        var username = context.getAuthentication().getName();
        var  role  =  context.getAuthentication().getAuthorities().toArray();


        Map<String, String> idToken = new HashMap<>();
        idToken.put("Authorization", "Bearer " + jwtAccessToken);
        response.setContentType("application/json");
        idToken.put("status" , HttpStatus.OK.name());
        idToken.put("message" ,  "you are authenticated");
        idToken.put("user" , username);
        idToken.put("role", role[0].toString());
        new ObjectMapper().writeValue(response.getOutputStream(), idToken);

    }


}
