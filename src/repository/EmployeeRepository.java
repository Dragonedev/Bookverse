package repository;

import java.util.HashMap;
import java.util.Map;

import entities.Employee;

public class EmployeeRepository {

	private Map<String, Employee> employees = new HashMap<>();

	public void save(Employee employee) {
		employees.put(employee.getEmployeeCode(), employee);
	}

	public Employee findByCode(String employeeCode) {
		return employees.get(employeeCode);
	}

	public void delete(String employeeCode) {
		employees.remove(employeeCode);
	}

	public boolean existsByCode(String employeeCode) {
		return employees.containsKey(employeeCode);
	}
}
