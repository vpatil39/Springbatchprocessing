package springbatchexample.itemWriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springbatchexample.beans.User;
import springbatchexample.repository.UserRepository;

@Component
public class DbWriter implements ItemWriter<User> {

	@Autowired
	UserRepository userRepository;

	@Override
	public void write(List<? extends User> users) throws Exception {
		
		System.out.println("data saved in users"+users);
		userRepository.saveAll(users);
	}
	
	

}
