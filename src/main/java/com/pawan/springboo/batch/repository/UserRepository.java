package com.pawan.springboo.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.springboo.batch.entity.User;

public interface UserRepository  extends JpaRepository<User, Long> {

}
