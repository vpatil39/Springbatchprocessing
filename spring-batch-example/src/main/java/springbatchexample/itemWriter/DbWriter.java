package springbatchexample.itemWriter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springbatchexample.beans.Department;
import springbatchexample.beans.UserDetails;
import springbatchexample.beans.UserSpecify;
import springbatchexample.repository.DeptRepo;
import springbatchexample.repository.UserSRepository;
//import springbatchexample.repository.UserRepository;
//import springbatchexample.repository.UserSpecifyRepository;

@Component
public class DbWriter implements ItemWriter<UserSpecify> {

	@Autowired
	UserSRepository userRepository;
	
	@Autowired
	DeptRepo deptrepo;

	@Override
	public void write(List<? extends UserSpecify> users1) throws Exception {

		System.out.println("data saved in users" + users1);
		List <UserDetails> userdetails=new ArrayList<>();
		List <Department> deptdetails=new ArrayList<>();
		users1.forEach(objects -> {
			 UserDetails usd=new UserDetails();
			 Department deptm=new Department();
			 usd.setId(objects.getUserdetails().getId());
			 usd.setName(objects.getUserdetails().getName());
			 deptm.setDeptname(objects.getDepartment().getDeptname());
			 deptm.setSalary(objects.getDepartment().getSalary());			 
			 userdetails.add(usd);
			 deptdetails.add(deptm);
		});
		
		userRepository.saveAll(userdetails);
		deptrepo.saveAll(deptdetails);
	}

}
