package it.clever.springsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



// deve abilitare enale Web Mvc, deve essere configuration.... (sono obligatorie) altrimenti non mi partono i cotnoller

@Configuration
@EnableWebMvc

// tutto parte dal custom server initialiter....  poi cerco gli altri packages...

//posso avere alta elasticitò... 

// perchè c'è bisogno di abilitarli? ... perche potrei gestire i controller con un altra app



@ComponentScan(basePackages = { "it.clever.springsec.*" })
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}
