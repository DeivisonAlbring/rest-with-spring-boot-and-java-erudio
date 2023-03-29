package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erudio.model.Books;

public interface BookRepository extends JpaRepository<Books, Long>{

}
