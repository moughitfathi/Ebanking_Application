package com.spring.ebanking.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Admin;
import com.spring.ebanking.entities.Personne;
import com.spring.ebanking.repositories.AdminRepository;
import com.spring.ebanking.repositories.PersonneRepository;
import com.spring.ebanking.repositories.RoleRepository;

import javassist.NotFoundException;

@Service
public class AdminService {
@Autowired
PersonneRepository personneRepository;

	
@Autowired 
AdminRepository adminRepository;
@Autowired
EmailService emailService;
@Autowired
RoleRepository roleRepository;


public Admin getByUsername(String username) throws Exception
{
	Personne p= personneRepository.findByUsername(username).orElseThrow(() -> new Exception("Aucun Admin avec l'username "+username+" trouvé"));
	return adminRepository.findById(p.getId()).orElseThrow(() -> new Exception("Aucun Admin avec l'username "+username+" trouvé"));
}



public Admin getById(Long id)throws NotFoundException {
		Admin admin = adminRepository.findById(id).orElseThrow(()->
			new NotFoundException("aucun Administratuer avec l'id = "+id+"est trouve")
				);
		return admin;
}

public List<Admin> getAdmins() throws NotFoundException{
	List<Admin> admins = new ArrayList<Admin>();
	admins=adminRepository.findAll();
	if(admins.isEmpty()) throw new NotFoundException("aucun administrateur est trouve");
	return admins;

} 

public void addAdmin(Admin admin)throws Exception {
	String password= admin.getPassword();
	if(adminRepository.findByEmail(admin.getEmail()).isPresent()) {
			throw new Exception("l'administrateur est deja existent erreur Email! ");
	}
	if(adminRepository.findByCin(admin.getCin()).isPresent()) {
		throw new Exception("l'administrateur est deja existent erreur CIN");
	}
	if(adminRepository.findByTel(admin.getTel()).isPresent()) {
		throw new Exception("l'administrateur est deja existent erreur Tel");
	}
	
	if(!password.isEmpty() && password != null) {
		admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
	}else {
		throw new Exception("password not specified!");
	}
	
	if(personneRepository.findByUsername(admin.getUsername()).isPresent())
		throw new Exception("try with anothor Username ");
	
	admin.setRole(roleRepository.findByRole("admin").orElseThrow(
			()-> new Exception("le role dont le no est admin est introuvable!")));
	admin.setDateInscription(new Date());

		adminRepository.save(admin);
		admin.setPassword(password);
		emailService.sendEmail(admin);
	
	
	
}

public void updateAdmin(Admin admin,Long id)  throws Exception{
	Admin adminAjour = adminRepository.findById(id).orElseThrow(() -> new Exception("admin avec l'id = "+id+"introuvable!"));
	String password = admin.getPassword();
	
	if (adminRepository.findByEmail(admin.getEmail()).isPresent() && !(adminRepository.findByEmail(admin.getEmail())).get().getEmail().equals(adminAjour.getEmail())) {
		throw new Exception("veuillez choisir un autre email");
	}
	if (adminRepository.findByCin(admin.getCin()).isPresent() && !(adminRepository.findByCin(admin.getCin())).get().getCin().equals(adminAjour.getCin())) {
		throw new Exception("veuillez choisir un autre cin");
	}
	if (adminRepository.findByTel(admin.getTel()).isPresent() && !(adminRepository.findByTel(admin.getTel())).get().getTel().equals(adminAjour.getTel())) {
		throw new Exception("veuillez choisir un autre Tel");
	}
	
	DateFormat dateFormat =  new SimpleDateFormat("yyyy-mm-dd");
	
	
	if(personneRepository.findByUsername(admin.getUsername()).isPresent() && !(personneRepository.findByUsername(admin.getUsername()).get().getUsername().equals(adminAjour.getUsername())))
		throw new Exception("try with anothor Username ");
	
//else
	if(admin.getUsername()!=null && !admin.getUsername().isEmpty()) adminAjour.setUsername(admin.getUsername());
	
	if(admin.getNom()!=null && !admin.getNom().isEmpty()) adminAjour.setNom(admin.getNom());
	if(admin.getPrenom()!=null && !admin.getPrenom().isEmpty()) adminAjour.setPrenom(admin.getPrenom());
	if(admin.getTel()!=null && !admin.getTel().isEmpty()) adminAjour.setTel(admin.getTel());
	if(admin.getEmail()!=null && !admin.getEmail().isEmpty()) adminAjour.setEmail(admin.getEmail());
	if(admin.getAdresse()!=null && !admin.getAdresse().isEmpty()) adminAjour.setAdresse(admin.getAdresse());
	if(admin.getPassword()!=null && !admin.getPassword().isEmpty()) adminAjour.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
	if(admin.getCin()!=null && !admin.getCin().isEmpty()) adminAjour.setCin(admin.getCin());
	if(admin.getDateNaissance()!=null && !(dateFormat.format(admin.getDateNaissance()).isEmpty())) adminAjour.setDateNaissance(admin.getDateNaissance());
	
	
	adminRepository.save(adminAjour);
	if (password!=null && !password.isEmpty()) {
		adminAjour.setPassword(password);
	}else {
		adminAjour.setPassword(null);
	}
	emailService.sendEmail(adminAjour);
	
	
}

public void removeAdmin(Long id)throws NotFoundException {
		Admin admin = adminRepository.findById(id).orElseThrow(()-> new NotFoundException("L'admin dont l'id est "+id+" est introuvable!"));
		adminRepository.delete(admin);
		
} 

	
	
}
