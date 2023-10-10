package com.api.demo.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.demo.models.PersonaCls;

@Repository
public interface PersonaDao extends JpaRepository<PersonaCls, Long> {

	Optional<PersonaCls> findByCedula(BigInteger cedula);
	
	


}
