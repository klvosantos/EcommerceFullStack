package com.marcelosantos.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.marcelosantos.cursomc.domain.Pedido;

public interface EmailService {
	
    void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
