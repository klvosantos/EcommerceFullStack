package com.marcelosantos.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.marcelosantos.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;			
		}
	}
}


// CLasse com metodo para pegar o usuario logado no sistema