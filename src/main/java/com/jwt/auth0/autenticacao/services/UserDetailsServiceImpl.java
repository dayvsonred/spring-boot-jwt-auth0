package com.jwt.auth0.autenticacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.auth0.autenticacao.config.UserSS;
import com.jwt.auth0.autenticacao.domain.users_bd;
import com.jwt.auth0.autenticacao.repositorys.Users_bdRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private Users_bdRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		users_bd cli = repo.findByEmail(email);
		if (cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getPassword(), cli.getPerfis());
	}
}
