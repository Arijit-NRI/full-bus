package nrifintech.busMangementSystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.Query;


import nrifintech.busMangementSystem.entities.User;


public interface UserRepo extends  JpaRepository<User, Integer>{


	@Query(value = "SELECT * FROM user WHERE email = ?1 AND type = ?2", nativeQuery = true)
	Optional<User> findByEmail(String email, int type);
	
	@Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
	User findByOnlyEmail(String email);

}
