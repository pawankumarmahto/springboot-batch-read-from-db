package com.pawan.springboo.batch.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pawan.springboo.batch.entity.DebitCard;
import com.pawan.springboo.batch.entity.User;
import com.pawan.springboo.batch.repository.DebittCardRepository;
import com.pawan.springboo.batch.repository.UserRepository;

@Service
public class JpaDebitCardServiceImpl implements JpaDebitCardService{
	
	@Autowired
	DebittCardRepository debittCardRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService  userService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDebitCard(DebitCard dc) {
		DebitCard newDc = debittCardRepository.save(dc);
		userService.saveUser(newDc.getCardNo());
	}
}
