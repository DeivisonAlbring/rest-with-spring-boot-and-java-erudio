package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.DTO.v1.BooksDTO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Books;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.services.BooksServices;
import br.com.erudio.unittests.mapper.mocks.MockPerson;
import br.com.erudio.unittests.mapper.mocks.mockBooks;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BooksServicesTest {
	
	mockBooks input;
	
	@InjectMocks
	private BooksServices service;
	
	@Mock
	BookRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new mockBooks();
		MockitoAnnotations.openMocks(this);		
	}

	@Test
	void testFindById() {
		
		Books entity = input.mockEntity(1);		
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());		
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Autor Teste 1", result.getAuthor());
		assertEquals(100, result.getPrice());
		assertEquals("Titulo Teste 1", result.getTitle());
		assertNotNull(result.getlaunchDate());
		
	}	
	
	@Test
	void testFindAll() {
		List<Books> list = input.mockEntityList();	
		
		when(repository.findAll()).thenReturn(list);
		
		var people = service.findAll();
		
		assertNotNull(people);
		assertEquals(14,people.size());
		
		var personOne = people.get(1);
		
		assertNotNull(personOne);
		assertNotNull(personOne.getKey());
		assertNotNull(personOne.getLinks());
		System.out.println(personOne.toString());		
		assertTrue(personOne.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Autor Teste 1", personOne.getAuthor());
		assertEquals(100, personOne.getPrice());
		assertEquals("Titulo Teste 1", personOne.getTitle());
		assertNotNull(personOne.getlaunchDate());
		
		
	}

	@Test
	void testCreate() {
		Books entity = input.mockEntity(1);
		Books persisted = entity;
		
		BooksDTO DTO = input.mockDTO(1);
		DTO.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(DTO);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());	
		
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Autor Teste 1", result.getAuthor());
		assertEquals(100, result.getPrice());
		assertEquals("Titulo Teste 1", result.getTitle());
		assertNotNull(result.getlaunchDate());
		
	}
	
	@Test
	void testCreateWithNullBook() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "it is no allowed to persist a null object! ";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));

	}
	
	void testUpdateWithNullBook() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "it is no allowed to persist a null object! ";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
	}

	@Test
	void testUpdate() {
		Books entity = input.mockEntity(1);
		entity.setId(1L);
		
		Books persisted = entity;
		persisted.setId(1L);
		
		BooksDTO DTO = input.mockDTO(1);
		DTO.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(DTO);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());	
		
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Autor Teste 1", result.getAuthor());
		assertEquals(100, result.getPrice());
		assertEquals("Titulo Teste 1", result.getTitle());
		assertNotNull(result.getlaunchDate());
		
	}

	@Test
	void testDelete() {
				
		Books entity = input.mockEntity(1);		
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
		
		
		
		
	}

}
