package com.pawan.springboo.batch.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pawan.springboo.batch.entity.DebitCard;

@Repository
public interface DebittCardRepository extends JpaRepository<DebitCard, Long> {

	@Query(value = "SELECT * FROM debit_card u WHERE u.status not in ('E') ", nativeQuery = true)
	public ArrayList<DebitCard> findNotExpiredDebitCard();
}
