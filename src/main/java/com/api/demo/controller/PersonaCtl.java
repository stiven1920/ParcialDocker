package com.api.demo.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.demo.models.PersonaCls;
import com.api.demo.services.PersonaSvs;

@RestController
@RequestMapping("/api")
public class PersonaCtl {

	@Autowired
	PersonaSvs servicePersona;

	@GetMapping("/getAllPersonas")
	public ArrayList<PersonaCls> getPersonas() {
		return this.servicePersona.getPersona();
	}

	@PostMapping("addPersona")
	public Object addPersona(@RequestBody PersonaCls persona) {
		try {
			Optional<PersonaCls> findPersona = servicePersona.getPersonaDni(persona.getCedula());
			if (findPersona.isPresent()) {
				return "Error: La persona con la cédula " + persona.getCedula() + " ya existe.";
			} else {
				return this.servicePersona.addPersona(persona);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Error al procesar la solicitud.";
		}
	}

	@GetMapping(path = "/getPersonaId/{id}")
	public Optional<PersonaCls> getPersonasId(@PathVariable("id") Long id) {
		return this.servicePersona.getPersonaId(id);
	}

	@GetMapping(path = "/getPersonaDni/{cedula}")
	public Optional<PersonaCls> getPersonaDni(@PathVariable("cedula") BigInteger cedula) {
		return this.servicePersona.getPersonaDni(cedula);
	}

	@PutMapping(path = "/updatePersona/{id}")
	public Optional<PersonaCls> updatePersona(@RequestBody PersonaCls persona, @PathVariable("id") Long id) {
		return servicePersona.updatePersonaId(persona, id);
	}

	@DeleteMapping(path = "/deletePersona/{id}")
	public String deletePersona(@PathVariable("id") Long id) {
		boolean ok = servicePersona.deletePersona(id);

		if (ok) {
			return "se ah elinado la persona: " + id;
		} else {
			return "Error al tratar de eliminar el id: " + id;
		}
	}

}
