package fr.sali.cantine.spring.security.filters;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sali.cantine.dto.in.LoginDto;
import fr.sali.cantine.dto.in.UserDtailsDto;
import fr.sali.cantine.dto.out.UserDtout;
import fr.sali.cantine.entity.RoleEntity;
import fr.sali.cantine.service.AuthentificationService;
import fr.sali.cantine.spring.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter  implements  SecurityConstants{

    private static final Logger LOG = LogManager.getLogger();
    private final AuthenticationManager authenticationManager;
    Environment env ;

    @Autowired
    AuthentificationService authenticationSerivce;

    public JwtAuthenticationFilter(AuthenticationManager pAuthenticationManager, Environment pEnv) {
        this.authenticationManager = pAuthenticationManager;
        this.setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
       // this.setAuthenticationFailureHandler(new StoneAuthenticationFailureHandler());

        //    Centraliser  les messages des  erreurs

        this.env = pEnv;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest  request, HttpServletResponse response) {
        var username = request.getParameter("email");
        var password = request.getParameter("password");

        LoginDto loginDtoIn = null;
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)) {
            LOG.debug("--> JwtAuthenticationFilter.attemptAuthentication(email, password) as Json in Body");
            String body = null;
            try {

                body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

                var mapper = new ObjectMapper();
                loginDtoIn = mapper.readValue(body, LoginDto.class);
                username = loginDtoIn.getEmail();
                password = loginDtoIn.getPassword();
                loginDtoIn.validate();
            } catch (Exception exp) {
                LOG.error("--> JwtAuthenticationFilter.attemptAuthentication - Error, your JSon is not right!, found {}, should be something like {\"email\":\"toto@gmail.com\",\"password\":\"bonjour\"}. DO NOT use simple quote!", body, exp);
            }

        } else {
            LOG.debug("--> JwtAuthenticationFilter.attemptAuthentication(email, password) as parameter");
        }
        LOG.debug("--> JwtAuthenticationFilter.attemptAuthentication({}, [PROTECTED])", username);

      //
        var result = this.authenticationSerivce.authentification(loginDtoIn);
        if (result == null ) {
            try {
                response.sendError(400 ,  "NOT FOUND");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Collection<GrantedAuthority> springSecurityRoles = new ArrayList<>(2);

        //pas besoin de username,  password   car ils  sont  passsé directement via l'objet authenticationToken
        UserDtailsDto userDtails  = new UserDtailsDto( result.getId(),result.getBirthday(), result.getEmail(), result.getUserfname() ,result.getRoles() );
        for (RoleEntity role   :  result.getRoles()) {
            GrantedAuthority ga = new SimpleGrantedAuthority(role.getLibelle().toString());
            springSecurityRoles.add(ga);
        }
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password, springSecurityRoles);;
         authenticationToken.setDetails(userDtails);
        return authenticationToken ;
    }

    /**
     *
     * @param request
     * @param response
     * @param filterChain
     * @param authentication ce param  a été remplacer directement par le authentificationSevrvice injecté avec authwireed
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ,  Authentication authentication) {

         var  userName   = authentication.getName();

         //  confier  a  spring  les roles
        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        var ue = (UserDtout) authentication.getDetails();
        Claims claims = new DefaultClaims();
        claims.put(SecurityConstants.TOKEN_USER, ue);
        claims.put(SecurityConstants.TOKEN_ROLES, roles);
        claims.setIssuer(SecurityConstants.TOKEN_ISSUER);
        claims.setAudience(SecurityConstants.TOKEN_AUDIENCE);
        claims.setSubject(userName);


        var val = Integer.parseInt(this.env.getProperty("configuration.jwt.expire.in.ms", "86400000"));
       // configurer le  time de expiration
        claims.setExpiration(new Date(System.currentTimeMillis() + val));

        var signatureAlgorithm = SignatureAlgorithm
                .forName(this.env.getProperty("configuration.jwt.signature.algorithm", "none"));

        JwtBuilder builder;


        //pas  compris  ce qui  se passe ici  ducoup
        if (signatureAlgorithm == null || signatureAlgorithm == SignatureAlgorithm.NONE) {
            LOG.warn("- No encryption for JWT token, this is good for testing ...");
            builder = Jwts.builder().setHeaderParam("typ", SecurityConstants.TOKEN_TYPE).setClaims(claims);
        }else{
            LOG.debug("Encryption for JWT token is {}, do not forget to set your key in the configuration file",signatureAlgorithm);

            var signingKey = this.env.getProperty("configuration.jwt.key", "-KaPdSgVkXp2s5v8y/B?E(H+MbQeThWmZq3t6w9z$C&F)J@NcRfUjXn2r5u7x!A%").getBytes();
            builder = Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), signatureAlgorithm).setHeaderParam("typ", SecurityConstants.TOKEN_TYPE).setClaims(claims);
        }


        // Sonar does not like this line because it thinks there is no signwith
        var token = builder.compact(); // NOSONAR
        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);


    }




   /*    ---- Le traitement des exception  en cas d'erreur
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException pException) throws IOException, ServletException {
        var out = new ExceptionDtoOut(pException);
        var objectMapper = new ObjectMapper();
        var expToJson = objectMapper.writeValueAsString(out);
        var pw = response.getWriter();
        pw.write(expToJson);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
   */

    }//  End  Of class
