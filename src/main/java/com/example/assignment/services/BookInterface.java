package com.example.assignment.services;

import java.util.List;

import com.example.assignment.model.Book;

public interface BookInterface {

	public List<Book> findAllBooks();
	public List<Book> findBooksByAvailability();
	public List<Book> findBooksByAuthorName(String authorName);
	public Book findBookById(Integer id);
}
