package com.example.assignment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lends")
public class Lends {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "book_id")
	private int bookId;

	@Column(name = "lend_date")
	private Date lendDate;

	@Column(name = "expiration_date")
	private Date expirationDate;

	@Column(name = "return_date")
	private Date returnDate;

	public Lends() {
	}

	public Lends(Integer id, int userId, int bookId, Date lendDate, Date expirationDate, Date returnDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.lendDate = lendDate;
		this.expirationDate = expirationDate;
		this.returnDate = returnDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getLendDate() {
		return lendDate;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
