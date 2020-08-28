package springbatchexample.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserDetails {
 @Id
	private Integer Id;
	private String name;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserDetails(Integer id, String name) {
		super();
		Id = id;
		this.name = name;
	}
	public UserDetails() {
		super();
	}
	
}
