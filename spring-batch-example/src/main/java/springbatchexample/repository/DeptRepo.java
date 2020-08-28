package springbatchexample.repository;

import org.springframework.data.repository.CrudRepository;

import springbatchexample.beans.Department;

public interface DeptRepo extends CrudRepository<Department, Integer > {
	//void saveAll(Department dept);
}
