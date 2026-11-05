package repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import entities.Employee;

public class EmployeeRepository {

    private final Map<String, Employee> employees = new HashMap<>();

    public void save(Employee employee) {

        employees.put(employee.getEmployeeCode(), employee);

    }

    public Optional<Employee> findByCode(String employeeCode) {

        return Optional.ofNullable(employees.get(employeeCode));

    }

    public void delete(String employeeCode) {

        employees.remove(employeeCode);

    }

    public boolean existsByCode(String employeeCode) {

        return employees.containsKey(employeeCode);

    }

}