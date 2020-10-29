package com.example.assignment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.Book;
import com.example.assignment.services.BookInterface;

@RestController
public class BookController {

	@Autowired
	BookInterface bookInterface;

	@ResponseBody
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
		return bookInterface.findAllBooks();
	}
	
	@ResponseBody
	@GetMapping("/getAvailableBooks")
	public List<Book> getAvailableBooks() {
		return bookInterface.findBooksByAvailability();
	}
	
	@ResponseBody
	@GetMapping("/getBooksByAuthorName/{authorName}")
	public List<Book> getBooksByAuthorName(@PathVariable("authorName") String authorName) {
		return bookInterface.findBooksByAuthorName(authorName);
	}
	
	@ResponseBody
	@GetMapping("/getBookById/{bookId}")
	public Book getBookById(@PathVariable("bookId") Integer bookId) {
		return bookInterface.findBookById(bookId);
	}
}
