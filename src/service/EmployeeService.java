package service;

import entities.Employee;
import exceptions.DuplicateResourceException;
import exceptions.InvalidDataException;
import exceptions.ResourceNotFoundException;
import repository.EmployeeRepository;

public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void save(Employee employee) {

        if (employee == null) {
            throw new InvalidDataException("Employee cannot be null.");
        }

        if (employee.getEmployeeCode() == null ||
                employee.getEmployeeCode().isBlank()) {
            throw new InvalidDataException("Employee code is required.");
        }

        if (employeeRepository.existsByCode(employee.getEmployeeCode())) {
            throw new DuplicateResourceException("Employee already exists.");
        }

        employeeRepository.save(employee);
    }

    public Employee findByCode(String employeeCode) {

        if (employeeCode == null || employeeCode.isBlank()) {
            throw new InvalidDataException("Employee code is required.");
        }

        return employeeRepository.findByCode(employeeCode)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
    }

    public void delete(String employeeCode) {

        findByCode(employeeCode);
        employeeRepository.delete(employeeCode);
    }

    public boolean existsByCode(String employeeCode) {

        if (employeeCode == null || employeeCode.isBlank()) {
            throw new InvalidDataException("Employee code is required.");
        }

        return employeeRepository.existsByCode(employeeCode);
    }
}