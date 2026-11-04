package service;

import entities.Employee;
import repository.EmployeeRepository;

public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	public Employee findByCode(String employeeCode) {
		return employeeRepository.findByCode(employeeCode);
	}

	public void delete(String employeeCode) {
		employeeRepository.delete(employeeCode);
	}

	public boolean existsByCode(String employeeCode) {
		return employeeRepository.existsByCode(employeeCode);
	}
}