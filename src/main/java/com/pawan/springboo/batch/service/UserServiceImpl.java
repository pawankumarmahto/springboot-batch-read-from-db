package com.pawan.springboo.batch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.springboo.batch.entity.User;
import com.pawan.springboo.batch.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void saveUser(Long cardNo) {
		User user = new User();
		user.setUserName("ABC_" + cardNo);
		userRepository.save(user);
	}
}
