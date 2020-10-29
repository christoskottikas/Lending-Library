package com.example.assignment.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.assignment.model.Lends;

public interface LendingRepository extends JpaRepository<Lends, Integer>{

	@Query("SELECT l FROM Lends l WHERE l.userId = :userId and l.bookId = :bookId and l.returnDate is null")
	Lends findReturnableLend(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
}
