package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.DTO.v1.PersonDTO;
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
			
		return DozerMapper.parseListObjects(repository.findAll(), PersonDTO.class);		
	}
	

	public PersonDTO findById(Long id) {
				
		logger.info("Finding one PersonDTO");			
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerMapper.parseObject(entity, PersonDTO.class);
		
	}
	
	public PersonDTO create(PersonDTO PersonDTO) {
		logger.info("Creating one PersonDTO");
		
		var entity = DozerMapper.parseObject(PersonDTO, Person.class);
		
		var DTO = DozerMapper.parseObject(repository.save(entity),PersonDTO.class);
		//comment
		return DTO;		
		
	}
	
	
	public PersonDTO update(PersonDTO PersonDTO) {
		logger.info("Updating one PersonDTO");
		
		var entity = repository.findById(PersonDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(PersonDTO.getFirstName());
		entity.setLastName(PersonDTO.getLastName());
		entity.setAddress(PersonDTO.getAddress());
		entity.setGender(PersonDTO.getGender());		
		
		var DTO = DozerMapper.parseObject(repository.save(entity),PersonDTO.class);
		
		return DTO;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one PersonDTO");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		repository.delete(entity);	
		
	}
	
	
}
