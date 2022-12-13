package fr.sali.cantine.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

abstract class AbstractSpringSecurityConfiguration {

    @Autowired
    protected Environment env;

      protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        // BEAN for children to load
        return authenticationConfiguration.getAuthenticationManager();
    }
}
