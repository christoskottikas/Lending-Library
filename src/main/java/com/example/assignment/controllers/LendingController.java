package com.example.assignment.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.Book;
import com.example.assignment.model.Lends;
import com.example.assignment.services.BookInterface;
import com.example.assignment.services.LendingInterface;

@RestController
public class LendingController {

	@Autowired
	LendingInterface lendingInterface;

	@Autowired
	BookInterface bookInterface;

	@ResponseBody
	@GetMapping("/lendBook/{userId}/{bookId}")
	public boolean lendBook(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId) {
		Book book = bookInterface.findBookById(bookId);
		Date currentDate = new Date();
		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime = localDateTime.plusDays(book.getLendingDays());
		Date expirationDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		try {
			Lends lend = new Lends();
			lend.setUserId(userId);
			lend.setBookId(bookId);
			lend.setLendDate(new Date());
			lend.setExpirationDate(expirationDate);
			lendingInterface.insertLend(lend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@ResponseBody
	@GetMapping("/returnBook/{userId}/{bookId}")
	public String returnBook(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId) {
		Lends lend = lendingInterface.findReturnableLend(userId, bookId);
		Date currentDate = new Date();
		
		try {
			lend.setReturnDate(currentDate);
			if (currentDate.after(lend.getExpirationDate())) {
				Book book = bookInterface.findBookById(bookId);
				LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				LocalDateTime expirationDate = lend.getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				long noOfDaysBetween = ChronoUnit.DAYS.between(localDateTime, expirationDate);
				float cost = (noOfDaysBetween)*(book.getCost());
				return "Successfull return, cost is:"+ cost;
			}else {
				return "Successfull return";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Something went wrong";
		}

	}

}
