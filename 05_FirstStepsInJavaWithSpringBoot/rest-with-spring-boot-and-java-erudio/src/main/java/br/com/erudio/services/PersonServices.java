package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.DTO.v1.PersonDTO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
		
	public List<PersonDTO> findAll() {
		
		logger.info("Finding all PersonDTO");
					
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonDTO.class);
		
		persons
			.stream()
			.forEach(p -> {
				try {
					p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});	
		
		return persons;
	}

	public PersonDTO findById(Long id) {
				
		logger.info("Finding one PersonDTO");			
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		PersonDTO DTO = DozerMapper.parseObject(entity, PersonDTO.class);
		
		try {
			DTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return DTO;
		
	}
	
	public PersonDTO create(PersonDTO PersonDTO) {
		
		if (PersonDTO == null) throw new RequiredObjectIsNullException();		
		logger.info("Creating one PersonDTO");		
		var entity = DozerMapper.parseObject(PersonDTO, Person.class);		
		var DTO = DozerMapper.parseObject(repository.save(entity),PersonDTO.class);
		
		try {
			DTO.add(linkTo(methodOn(PersonController.class).findById(DTO.getKey())).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}		
		//
		return DTO;		
		
	}
	
	public PersonDTO update(PersonDTO PersonDTO) {
		
		if (PersonDTO == null) throw new RequiredObjectIsNullException();		
		logger.info("Updating one PersonDTO");		
		var entity = repository.findById(PersonDTO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(PersonDTO.getFirstName());
		entity.setLastName(PersonDTO.getLastName());
		entity.setAddress(PersonDTO.getAddress());
		entity.setGender(PersonDTO.getGender());		
		
		var DTO = DozerMapper.parseObject(repository.save(entity),PersonDTO.class);
		
		try {
			DTO.add(linkTo(methodOn(PersonController.class).findById(DTO.getKey())).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DTO;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one PersonDTO");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		repository.delete(entity);	
		
	}
	
	
}
