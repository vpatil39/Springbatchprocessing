package springbatchexample.processor;

import java.util.HashMap;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import springbatchexample.beans.Department;
import springbatchexample.beans.User;
import springbatchexample.beans.UserDetails;
import springbatchexample.beans.UserSpecify;

@Component
public class Processor implements ItemProcessor<User, UserSpecify> {

	private static final java.util.Map<String, String> DEPT_Name = new HashMap<String, String>();

	public Processor() {
		DEPT_Name.put("101", "technology");
		DEPT_Name.put("102", "operations");
		DEPT_Name.put("103", "accounts");
	}

	/*
	 * @Override public UserSpecify process(UserSpecify users) throws Exception {
	 * 
	 * String deptString = users.getDepartment().getDeptname(); String
	 * dept=DEPT_Name.get(deptString);
	 * 
	 * user.setDept(dept);
	 * System.out.println(String.format("converted form the {%s} to {%s}",deptString
	 * ,dept)); return UserSpecify; }
	 */

	@Override
	public UserSpecify process(User item) throws Exception {
		String deptString = item.getDept();
		String dept=DEPT_Name.get(deptString);
		UserSpecify us=new UserSpecify();
		UserDetails details=new UserDetails();
		details.setId(item.getId());
		details.setName(item.getName());
		Department department=new Department();
		department.setSalary(item.getSalary());
		department.setDeptname(dept);
		us.setUserdetails(details);
		us.setDepartment(department);
		
		return us;
	}

}
