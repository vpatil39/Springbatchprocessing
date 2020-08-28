/**
 * 
 */
package springbatchexample.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vpatil39
 *
 */
@Entity
@Table
public class Department {
	 @Id 
	 @GeneratedValue(strategy=GenerationType.AUTO) long id;
	private Integer salary;
	
	private String deptname;
	//private Integer salary;
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Department(Integer salary, String deptname) {
		super();
		this.salary = salary;
		this.deptname = deptname;
	}
	public Department() {
		super();
	}
	
}
