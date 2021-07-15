package com.jwt.auth0.autenticacao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.auth0.autenticacao.domain.users_bd;
import com.jwt.auth0.autenticacao.dto.CredenciaisDTO;
import com.jwt.auth0.autenticacao.services.User_bdServices;

@RestController
@RequestMapping(value="/users")
public class user_bdController {
	
	@Autowired
	private User_bdServices service;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<users_bd>> findall() {
		List<users_bd> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(path="/pass", method=RequestMethod.GET)
	public ResponseEntity<String> getPass() {
		
		System.out.println(pe.encode("123456"));
		
		return ResponseEntity.ok().body(pe.encode("123456"));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<users_bd> find(@PathVariable Integer id) {
		users_bd obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	

	@RequestMapping(path="/pass", method=RequestMethod.POST)
	public ResponseEntity<String> getCheckPass(@RequestBody CredenciaisDTO objDto) {
		
		System.out.println("validando senha ----------------------");
		System.out.println(objDto.getPassword());
		
		users_bd cli = service.findByEmail(objDto.getEmail());
		
		System.out.println("email usuario");
		System.out.println(cli.getPassword());
		
		boolean isPasswordMatch = pe.matches(objDto.getPassword(), cli.getPassword());
		
		
		
		
		
		return  ResponseEntity.ok().body("is valid " + isPasswordMatch);
	}
	
	

	
	


}
