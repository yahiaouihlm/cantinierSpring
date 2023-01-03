package fr.sali.cantine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

@SpringBootApplication
public class CantineApplication {

	public static void main(String[] args) {
		 SpringApplication.run(CantineApplication.class, args);

	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder (){
		return  new BCryptPasswordEncoder();
	}


}
