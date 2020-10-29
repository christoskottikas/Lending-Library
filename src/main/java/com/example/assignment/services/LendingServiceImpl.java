package com.example.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.assignment.model.Lends;
import com.example.assignment.repositories.LendingRepository;

@Service
@Transactional
public class LendingServiceImpl implements LendingInterface {

	@Autowired
	LendingRepository lendingRepository;

	@Override
	public void insertLend(Lends lend) {
		lendingRepository.save(lend);
	}

	@Override
	public Lends findReturnableLend(Integer userId, Integer bookId) {
		return lendingRepository.findReturnableLend(userId, bookId);
	}
}
