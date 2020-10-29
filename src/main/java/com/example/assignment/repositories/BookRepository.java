package com.example.assignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.assignment.model.Book;

@Repository
public interface BookRepository  extends JpaRepository<Book, Integer>{

	@Query(value = "SELECT * FROM BOOKS", nativeQuery = true)
	List<Book> findAll();
	
	@Query(value = "SELECT * FROM BOOKS WHERE availability > 0", nativeQuery = true)
	List<Book> findAvailableBooks();
	
	@Query("SELECT b FROM Book b WHERE b.author LIKE %:authorName%")
	List<Book> findBookByAuthor(@Param("authorName") String authorName);
}
