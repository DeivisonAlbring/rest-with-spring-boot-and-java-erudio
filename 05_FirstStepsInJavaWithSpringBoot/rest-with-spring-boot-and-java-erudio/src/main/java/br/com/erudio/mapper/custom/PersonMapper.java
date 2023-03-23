package br.com.erudio.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.erudio.data.DTO.v2.PersonDTOv2;
import br.com.erudio.model.Person;

@Service
public class PersonMapper {
	
	public PersonDTOv2 convertEntityToDto(Person person) {
		PersonDTOv2 DTO = new PersonDTOv2();
		
		DTO.setId(person.getId());
		DTO.setAddress(person.getAddress());
		DTO.setBirthDay(new Date());
		DTO.setFirstName(person.getFirstName());
		DTO.setLastName(person.getLastName());
		DTO.setGender(person.getGender());
				
		return DTO;
		
	}
	
	public Person convertDtoToEntity(PersonDTOv2 person) {		
		Person entity = new Person();
		
		entity.setId(person.getId());
		entity.setAddress(person.getAddress());
//		entity.setBirthDay(new Date());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		
		return entity;		
		
	}
	

}
