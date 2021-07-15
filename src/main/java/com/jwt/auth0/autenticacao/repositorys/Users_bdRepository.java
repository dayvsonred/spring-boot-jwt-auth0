package com.jwt.auth0.autenticacao.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.auth0.autenticacao.domain.users_bd;

@Repository
public interface Users_bdRepository extends JpaRepository<users_bd, Integer> {
	users_bd findByEmail(String email);
}
