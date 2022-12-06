package fr.sali.cantine.spring.security.filters;

import fr.sali.cantine.dto.out.UserDtout;
import fr.sali.cantine.service.AuthentificationService;
import fr.sali.cantine.spring.SecurityConstants;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthorization extends BasicAuthenticationFilter implements SecurityConstants  {
    private static final Logger LOG = LogManager.getLogger();
    private  Environment env ;
    private final byte[] signingKey;

    @Autowired
    AuthentificationService authentificationService ;

    public JwtAuthorization(AuthenticationManager pAuthenticationManager, Environment pEnv) {
        super(pAuthenticationManager);
        this.signingKey = pEnv.getProperty("configuration.jwt.key",
                "-KaPdSgVkXp2s5v8y/B?E(H+MbQeThWmZq3t6w9z$C&F)J@NcRfUjXn2r5u7x!A%").getBytes();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        final var url = request.getRequestURL().toString();
       //  recupération du  Token
        var jwtToken = request.getHeader(SecurityConstants.TOKEN_HEADER);

        LOG.trace("<-- JwtAuthorizationFilter.doFilterInternal - {} - JWT token is {}", url,
                jwtToken);

        if (ObjectUtils.isEmpty(jwtToken) || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
          LOG.warn("<-- JwtAuthorizationFilter.doFilterInternal - {} - JWT token is Empty", url);
        }
        else if ( !this.validateToken(jwtToken)){
            LOG.error("<-- JwtAuthorizationFilter.doFilterInternal - {} - JWT token is Invalid", url);
        }else{
            Authentication authentication =  this.getAuthentication(jwtToken) ;
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOG.debug("<-- JwtAuthorizationFilter.doFilterInternal - {} - OK - Set authentication back", url);
        }

        filterChain.doFilter(request, response);

    }



    private  boolean validateToken (String  token){
         LOG.trace("--> JwtAuthorizationFilter.validateToken - Token - {}", token);

         try {
             Jwts.parserBuilder().setSigningKey(this.signingKey).build()
                     .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
             LOG.trace("--> JwtAuthorizationFilter.validateToken - Token is OK");
             return  true ;
         }catch (  Exception e){
             LOG.error("--> JwtAuthorizationFilter.validateToken - Token is KO", e);
         }

         return  false ;
    }

    /**
     * @doc ;  Rebuild UsernamePasswordAuthenticationToken for SpringSecurity from JWT token
     * @param token
     * @return ;  the token  for  Spring Security
     */

    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        LOG.debug("--> JwtAuthorizationFilter.getAuthentication - Token - {}", token);
        try {
            var parsedToken = Jwts.parserBuilder().setSigningKey(this.signingKey).build().
                    parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));

            var username = parsedToken.getBody().getSubject();
            Collection<? extends GrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
                    .get(SecurityConstants.TOKEN_ROLES)).stream()
                    .map(authority -> new SimpleGrantedAuthority((String) authority)).collect(Collectors.toList());

            if (!ObjectUtils.isEmpty(username)) {
                var resu = new UsernamePasswordAuthenticationToken(username, null, authorities);
                @SuppressWarnings("unchecked")
                Map<String, ?> userDto = (Map<String, ?>) parsedToken.getBody().get(SecurityConstants.TOKEN_USER);
                LOG.trace("val {}", userDto);

                var userDtoOut = new UserDtout(userDto);
                resu.setDetails(userDtoOut);
                LOG.warn("<-- JwtAuthorizationFilter.getAuthentication - Token was pushed into Spring Security, {}", resu);
                return resu;
            }

        }catch (  Exception exception ){
            LOG.error("- JwtAuthorizationFilter.getAuthentication : {} failed", token,
                    exception);
        }

        return null ;
    }




}
