package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.erudio.util.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.DTO.v1.PersonDTO;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
	
	@GetMapping(value = "/",			
			produces = {
					MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	public List<PersonDTO> findAll() {		
		return service.findAll();
	}
	
	
	@GetMapping(value = "/{id}",			
			produces = {
					MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	public PersonDTO findById(@PathVariable(value = "id") Long id) throws Exception {		
		return service.findById(id);
	}
	
	
	@PostMapping(
			produces = {
					MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML},
			consumes = {
					MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	public PersonDTO create(@RequestBody PersonDTO PersonDTO) throws Exception {		
		return service.create(PersonDTO);
	}
		
	@PutMapping(
			produces = {
					MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML},
			consumes = {
					MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	public PersonDTO update(@RequestBody PersonDTO PersonDTO) throws Exception {		
		return service.update(PersonDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
		
	
	
}
