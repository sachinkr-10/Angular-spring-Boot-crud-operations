package com.exm.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exm.demo.exception.ResourceNotFoundException;
import com.exm.demo.model.Employee;
import com.exm.demo.services.EmployeeServices;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
 	private EmployeeServices empServ;
    
    //create employee rest_end_point
    @PostMapping("/addEmp")
	public ResponseEntity<Employee> toInsert(@RequestBody Employee employee ){
    	employee=empServ.addEmployee(employee);
		return new ResponseEntity<>(employee,HttpStatus.CREATED);
		
	}
    //get all employees_rest_end
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmp(){
    	return new ResponseEntity<List<Employee>>(empServ.getAllEmployee(),HttpStatus.OK);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable Long id) throws ResourceNotFoundException{
    	return new ResponseEntity<Employee>(empServ.getEmpById(id),HttpStatus.OK);
    }
    
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable Long id,@RequestBody Employee emp) throws ResourceNotFoundException{
    	
    	return new ResponseEntity<>(empServ.updateEmployee(id, emp),HttpStatus.OK);
    }
    
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deletEmp(@PathVariable Long id) throws ResourceNotFoundException{
    	return new ResponseEntity<>(empServ.deleteEmpById(id),HttpStatus.OK);
    }
    
}
