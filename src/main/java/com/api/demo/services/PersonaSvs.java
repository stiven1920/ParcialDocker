package com.api.demo.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.demo.models.PersonaCls;
import com.api.demo.repository.PersonaDao;

@Service
public class PersonaSvs {

	@Autowired
	PersonaDao personaDao;

	public ArrayList<PersonaCls> getPersona() {
		return (ArrayList<PersonaCls>) personaDao.findAll();
	}

	public PersonaCls addPersona(PersonaCls persona) {
		return personaDao.save(persona);
	}

	public Optional<PersonaCls> getPersonaId(Long id) {
		return personaDao.findById(id);
	}
	
	public Optional<PersonaCls> getPersonaDni(BigInteger cedula) {
		return personaDao.findByCedula(cedula);
	}

	public Optional<PersonaCls> updatePersonaId(PersonaCls request, Long id) {
		PersonaCls persona = personaDao.findById(id).get();

		persona.setCedula(request.getCedula());
		persona.setNombre(request.getNombre());
		persona.setApellido(request.getApellido());
		persona.setEdad(request.getEdad());
		persona.setTelefono(request.getTelefono());
		persona.setEmail(request.getEmail());

		return Optional.of(persona);
	}

	public boolean deletePersona(Long id) {
		try {
			personaDao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
