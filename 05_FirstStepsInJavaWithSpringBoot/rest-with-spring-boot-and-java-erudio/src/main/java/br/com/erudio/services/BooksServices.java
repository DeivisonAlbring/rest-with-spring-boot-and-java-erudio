package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.BooksController;
import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.DTO.v1.BooksDTO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Books;
import br.com.erudio.repositories.BookRepository;

@Service
public class BooksServices {

	private Logger logger = Logger.getLogger(BooksServices.class.getName());
	
	@Autowired
	BookRepository repository;
	
	public List<BooksDTO> findAll(){
		
		logger.info("Finding all books");

		var books = DozerMapper.parseListObjects(repository.findAll(), BooksDTO.class);
		
		books
		.stream()
		.forEach(p -> {
			try {
				p.add(linkTo(methodOn(BooksController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});	
		
		return books;
		
	}
	
	public BooksDTO findById(Long id) {
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		BooksDTO DTO = DozerMapper.parseObject(entity, BooksDTO.class);
		
		try {
			DTO.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return DTO;
	}
	
	public BooksDTO create(BooksDTO booksDTO) {
		
		if (booksDTO == null) throw new RequiredObjectIsNullException();
		logger.info("Creating one Book");
		
		var entity = DozerMapper.parseObject(booksDTO, Books.class);		
		var DTO = DozerMapper.parseObject(repository.save(entity),BooksDTO.class);
		
		try {
			DTO.add(linkTo(methodOn(BooksController.class).findById(DTO.getKey())).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}		
		
		return DTO;
	}
	
	public BooksDTO update(BooksDTO booksDTO) {
		
		if (booksDTO == null) throw new RequiredObjectIsNullException();		
		logger.info("Updating one PersonDTO");		
		var entity = repository.findById(booksDTO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setAuthor(booksDTO.getAuthor());
		entity.setTitle(booksDTO.getTitle());
		entity.setPrice(booksDTO.getPrice());
		entity.setLaunchDate(booksDTO.getlaunchDate());
		
		var DTO = DozerMapper.parseObject(repository.save(entity),BooksDTO.class);
		
		try {
			DTO.add(linkTo(methodOn(BooksController.class).findById(DTO.getKey())).withSelfRel());
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
