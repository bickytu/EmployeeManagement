package com.springboot.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dao.EmployeeDao;
import com.springboot.entity.Employee;

@RestController
@RequestMapping("/api/v1/")
public class Controller {

	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeDao.findAll();
	}
	
	@PostMapping("/createemployee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeDao.save(employee);
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Optional optional = employeeDao.findById(employeeId);
		return (Employee) optional.get();
	}
	
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable int employeeId, Employee employee) {
		Optional optional = employeeDao.findById(employeeId);
		Employee emp =  (Employee) optional.get();
		
		emp.setEmployeeName(employee.getEmployeeName());
		emp.setEmailId(employee.getEmailId());
		emp.setPhoneNumber(employee.getPhoneNumber());
		
		return employeeDao.save(emp);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmplyee(@PathVariable int employeeId) {
		employeeDao.deleteById(employeeId);
		Map<String, Boolean>  resp = new HashMap<String,Boolean>();
		resp.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(resp);
	}
	
}
