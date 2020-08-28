package springbatchexample.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import springbatchexample.beans.UserDetails;

@EnableJpaRepositories
public interface UserSRepository extends CrudRepository<UserDetails, Integer > {
//	void saveAll(UserDetails userdetails);
	
	

}
