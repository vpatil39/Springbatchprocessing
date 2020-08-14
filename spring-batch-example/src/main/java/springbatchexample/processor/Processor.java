package springbatchexample.processor;

import java.util.HashMap;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import springbatchexample.beans.User;

@Component
public class Processor implements ItemProcessor<User, User> {

	private static final java.util.Map<String, String> DEPT_Name = new HashMap<String, String>();

	public Processor() {
		DEPT_Name.put("101", "technology");
		DEPT_Name.put("102", "operations");
		DEPT_Name.put("103", "accounts");
	}

	@Override
	public User process(User user) throws Exception {

		String deptString = user.getDept();
		String dept=DEPT_Name.get(deptString);
		user.setDept(dept);
		System.out.println(String.format("converted form the {%s} to {%s}",deptString,dept));
		return user;
	}

}
