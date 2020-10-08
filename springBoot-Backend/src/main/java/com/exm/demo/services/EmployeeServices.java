package com.exm.demo.services;

import java.util.HashMap;
//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exm.demo.exception.ResourceNotFoundException;
import com.exm.demo.model.Employee;
import com.exm.demo.repository.EmployeeRepository;

@Service
public class EmployeeServices {
	
	@Autowired
    private EmployeeRepository empRepo;
	
	//add an employee
	public Employee addEmployee(Employee employee){
		 empRepo.save(employee) ;
		 return employee;
	}
	//get lst of all employees
	public List<Employee> getAllEmployee(){
		      return empRepo.findAll();
	}
	
	//get employees by Id
	public Employee getEmpById(Long id) throws ResourceNotFoundException{
		return empRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee with id= "+id+" doesn not exists"));
	}
	//update an employee by id;
	public Employee updateEmployee(Long id,Employee emp) throws ResourceNotFoundException {
		Employee updateEmpDetails=getEmpById(id);//get prev emp details and update
		updateEmpDetails.setFirstName(emp.getFirstName());
		updateEmpDetails.setLastName(emp.getLastName());
		updateEmpDetails.setEmailId(emp.getEmailId());
		empRepo.save(updateEmpDetails);
    	
        return 	updateEmpDetails;
	}
	//delete an employee
	public Map<String,Boolean> deleteEmpById(Long id) throws ResourceNotFoundException{
		Employee emp=getEmpById(id);
		empRepo.delete(emp);
		Map<String,Boolean> hMap=new HashMap<>();
		hMap.put("delted", Boolean.TRUE);
		return hMap;
	}
}
