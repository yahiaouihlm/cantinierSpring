package fr.sali.cantine;

import ch.qos.logback.core.net.SyslogOutputStream;
import fr.sali.cantine.controleur.IncriptionControleur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

@SpringBootApplication
public class CantineApplication {


	public static void main(String[] args) {
		SpringApplication.run(CantineApplication.class, args);

	}



}
