package com.example.assignment.services;

import com.example.assignment.model.Lends;

public interface LendingInterface {

	public void insertLend(Lends lend);
	public Lends findReturnableLend(Integer userId, Integer bookId);
}
