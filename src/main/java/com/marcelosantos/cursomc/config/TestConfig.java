package com.marcelosantos.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcelosantos.cursomc.services.DBService;
import com.marcelosantos.cursomc.services.EmailService;
import com.marcelosantos.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		dbService.instantiateTestDatabase();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();		
	}
}

// Métodos com anotação @Bean estarão disponiveis como componentes para todo o sistema
// Se é feito a injeção de depêndencia em outra classe, o Spring procurara por um componente que pode ser um @Bean devolvendo uma instancia declarada no metodo
// Ex:se for injetado a dependencia EmailService em alguma classe, o spring reconhecera o @bean do e EmailService e retornara uma nova instancia MockEmailService() de forma automatica.