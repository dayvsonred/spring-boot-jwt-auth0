package com.jwt.auth0.autenticacao.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.auth0.autenticacao.domain.users_bd;
import com.jwt.auth0.autenticacao.repositorys.Users_bdRepository;


@Service
public class User_bdServices {
	
	@Autowired
	private Users_bdRepository repo;
	
	public List<users_bd> findAll() {
		return repo.findAll();
	}
	
	public users_bd find(Integer id) {
		Optional<users_bd> obj = repo.findById(id);
		return obj.orElseThrow(
					() -> null
				);
	}
	
	public users_bd findByEmail(String email) {
		users_bd obj = repo.findByEmail(email);
		
		if (obj == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return obj;
	}

}
