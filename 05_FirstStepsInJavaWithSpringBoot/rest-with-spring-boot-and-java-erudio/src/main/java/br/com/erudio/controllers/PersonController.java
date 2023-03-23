package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import br.com.erudio.data.DTO.v2.PersonDTOv2;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
	
	@GetMapping(value = "/",			
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> findAll() {		
		return service.findAll();
	}
	
	
	@GetMapping(value = "/{id}",			
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO findById(@PathVariable(value = "id") Long id) throws Exception {		
		return service.findById(id);
	}
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO create(@RequestBody PersonDTO PersonDTO) throws Exception {		
		return service.create(PersonDTO);
	}
	
	@PostMapping(value = "/v2",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTOv2 createv2(@RequestBody PersonDTOv2 Person) throws Exception {		
		return service.createv2(Person);
	}
		
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO update(@RequestBody PersonDTO PersonDTO) throws Exception {		
		return service.update(PersonDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
		
	
	
}
