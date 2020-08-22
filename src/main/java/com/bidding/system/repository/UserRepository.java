package com.bidding.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bidding.system.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
