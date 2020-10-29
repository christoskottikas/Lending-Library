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

	/**
	 * In this method we calculate the expiration date of the lend and then we insert the lent into the database via the given userId and bookId.
	 * @param userId
	 * @param bookId
	 * @return true if it is completed successfully.
	 */
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

	/**
	 * In this method we check if the return date is after the expiration date in order to calculate the cost and we update the lend into the database via the given userId and bookId.
	 * @param userId
	 * @param bookId
	 * @return a String message.
	 */
	@ResponseBody
	@GetMapping("/returnBook/{userId}/{bookId}")
	public String returnBook(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId) {
		Lends lend = lendingInterface.findReturnableLend(userId, bookId);
		Date currentDate = new Date();
		
		try {
			lend.setReturnDate(currentDate);
			lendingInterface.insertLend(lend);
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
