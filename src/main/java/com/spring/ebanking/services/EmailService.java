package com.spring.ebanking.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Admin;
import com.spring.ebanking.entities.Banquier;
import com.spring.ebanking.entities.Client;
import com.spring.ebanking.entities.CreneauDispo;

@Service
public class EmailService {
		
	
	@Autowired
	public JavaMailSender emailSender;
	
	public void sendEmail(Client c) throws MailException{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(c.getEmail());
		message.setSubject("recuperation de mot de passe");
		message.setText(
        		"Bonjour Cher Client"+c.getNom()+" "+c.getPrenom()+", \n"
        		+"Veuillez trouver ci-dessous un récapulatif sur votre compte :\n"
        		+"\nemail : "+c.getEmail()
        		+"\nmot de passe : "+c.getPassword()
        		+"\nCordialement."
        		);
        emailSender.send(message);
			
	}
	
	public void sendEmail(Banquier b) throws MailException{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(b.getEmail());
		message.setSubject("recuperation de mot de passe");
		message.setText(
        		"Bonjour Cher Agent"+b.getNom()+" "+b.getPrenom()+", \n"
        		+"Veuillez trouver ci-dessous un récapulatif sur votre compte :\n"
        		+"\nemail : "+b.getEmail()
        		+"\nmot de passe : "+b.getPassword()
        		+"\nCordialement."
        		);
        emailSender.send(message);
			
	}
	public void sendEmail(Admin a) throws MailException{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(a.getEmail());
		message.setSubject("recuperation de mot de passe");
		message.setText(
        		"Bonjour Admin"+a.getNom()+" "+a.getPrenom()+", \n"
        		+"Veuillez trouver ci-dessous un récapulatif sur votre compte :\n"
        		+"\nemail : "+a.getEmail()
        		+"\nmot de passe : "+a.getPassword()
        		+"\nCordialement."
        		);
        emailSender.send(message);
			
	}
	public void sendConfirmationRendez_vous(Banquier b, Client c,CreneauDispo cr) throws MailException{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(b.getEmail());
		message.setSubject("confirmation de rendez-vous");
		message.setText(
        		"Bonjour Cher Agent"+b.getNom()+" "+b.getPrenom()+", \n"
        		+"Le client"+c.getNom()+" "+b.getPrenom()+ "a pris un rendez-vous avec vous"
        		+ "pour la date:" + cr.getDateDebut()+" :\n"
        		+"\nCordialement."
        		);
        emailSender.send(message);
			
	}
	public void sendConfirmationRendez_vous(Client c,Banquier b, CreneauDispo cr) throws MailException{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(b.getEmail());
		message.setSubject("confirmation de rendez-vous");
		message.setText(
        		"Bonjour Cher Client"+c.getNom()+" "+c.getPrenom()+", \n"
        		+"vous avez pris un rendez-vous avec l'agent: "+b.getNom()+" "+b.getPrenom()+ "\n"
        		+ "pour la date:" + cr.getDateDebut()+" :\n"
        		+"\nCordialement."
        		);
        emailSender.send(message);
			
	}
	
	
}
