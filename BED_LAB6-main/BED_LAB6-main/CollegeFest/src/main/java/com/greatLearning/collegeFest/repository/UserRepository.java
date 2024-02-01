package com.greatLearning.collegeFest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greatLearning.collegeFest.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	/*Optional<User> findByEmail(String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}*/
	//@Query(value = "select u.* from User u where u.name = ?1", nativeQuery = true)
	//public User findByUsername(String name);
	@Query("select u from User u where u.username = ?1")
	User findByUsername(String username);
}
