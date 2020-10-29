package com.example.assignment.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "description")
	private String description;

	@Column(name = "availability")
	private int availability;

	@Column(name = "cost")
	private float cost;

	@Column(name = "lending_days")
	private int lendingDays;

	@ManyToMany(mappedBy = "bookCollection")
	@JsonBackReference
	private Collection<User> userCollection;

	public Book() {
	}

	public Book(Integer id, String title, String author, String description, int availability, float cost,
			int lendingDays) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.availability = availability;
		this.cost = cost;
		this.lendingDays = lendingDays;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getLendingDays() {
		return lendingDays;
	}

	public void setLendingDays(int lendingDays) {
		this.lendingDays = lendingDays;
	}

	public Collection<User> getUserCollection() {
		return userCollection;
	}

	public void setUserCollection(Collection<User> userCollection) {
		this.userCollection = userCollection;
	}

}
