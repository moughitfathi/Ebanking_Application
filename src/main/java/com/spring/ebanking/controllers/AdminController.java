package com.spring.ebanking.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ebanking.entities.Admin;
import com.spring.ebanking.services.AdminService;

import javassist.NotFoundException;

@RestController
@CrossOrigin(origins = "*")

public class AdminController {

	@Autowired 
	AdminService adminService;
	
	@GetMapping("/admin")
	@ResponseStatus(HttpStatus.OK)
	public Admin getAdmin(@RequestParam(name="id") Long id) throws NotFoundException
	{
		return adminService.getById(id);
	}
	
	@GetMapping("/admins")
	@ResponseStatus(HttpStatus.OK)
	public List<Admin> getAdmins(@RequestParam(name="id", required=false) Long id) throws NotFoundException
	{
		return adminService.getAdmins();
	}
	
	
	
	@GetMapping("/admin/username/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Admin getByUsername(@PathVariable(name="username") String username) throws Exception
	{
		return adminService.getByUsername(username);
	}
	
	
	



	
	@PostMapping("/admins")
	@ResponseStatus(HttpStatus.CREATED)
	public void addAdmin(@RequestBody Admin admin) throws Exception
	{
		adminService.addAdmin(admin);
	}




	
	@PutMapping("/admin/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateAdmin(@PathVariable Long id , @RequestBody(required=false) Admin admin) throws Exception
	{
		adminService.updateAdmin(admin,id);
	}


	
//DELETE
	
	@DeleteMapping("/admin/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAdmin(@PathVariable Long id) throws NotFoundException
	{
		adminService.removeAdmin(id);
	}
	
	
}
