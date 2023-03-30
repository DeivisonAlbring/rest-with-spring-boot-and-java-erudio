package br.com.erudio.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.erudio.data.DTO.v1.BooksDTO;
import br.com.erudio.model.Books;

public class mockBooks {
	
	public Books mockEntity() {
		return mockEntity(0);
	}
	
	public BooksDTO mockDTO() {
		return mockDTO(0);
	}
	
    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksDTO> mockDTOList() {
        List<BooksDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }
	
	public Books mockEntity(Integer number) {
		Books book = new Books();
		book.setAuthor("Autor Teste "+number);
		book.setLaunchDate(new Date());
		book.setPrice(100);
		book.setTitle("Titulo Teste "+number);
		book.setId(number.longValue());
		
		return book;
		
	}
	
	public BooksDTO mockDTO(Integer number) {
		BooksDTO book = new BooksDTO();
		book.setAuthor("Autor Teste "+number);
		book.setlaunchDate(new Date());
		book.setPrice(100);
		book.setTitle("Titulo Teste "+number);
		book.setKey(number.longValue());
		
		return book;
	}
	

}
