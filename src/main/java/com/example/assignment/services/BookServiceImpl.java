package com.example.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.assignment.model.Book;
import com.example.assignment.repositories.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookInterface{

	@Autowired
	BookRepository bookRepository;
	
	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findBooksByAvailability() {
		return bookRepository.findAvailableBooks();
	}

	@Override
	public List<Book> findBooksByAuthorName(String authorName) {
		return bookRepository.findBookByAuthor(authorName);
	}

	@Override
	public Book findBookById(Integer id) {
		return bookRepository.findById(id).get();
	}

}
