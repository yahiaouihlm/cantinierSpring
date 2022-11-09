package fr.sali.cantine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CantineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CantineApplication.class, args);

	}


	@Bean
	public BCryptPasswordEncoder passwordEncoded(){
		return  new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
	}
}
