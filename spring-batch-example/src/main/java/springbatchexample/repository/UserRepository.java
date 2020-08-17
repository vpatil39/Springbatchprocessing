package springbatchexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springbatchexample.beans.User;

public interface UserRepository extends JpaRepository<User, Integer > {
	
	

}
