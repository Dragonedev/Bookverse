package controller;

import entities.Employee;
import service.EmployeeService;

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void createEmployee(Employee employee) {
        employeeService.save(employee);
    }

    public Employee findEmployee(String employeeCode) {
        return employeeService.findByCode(employeeCode);
    }

    public void deleteEmployee(String employeeCode) {
        employeeService.delete(employeeCode);
    }

    public boolean employeeExists(String employeeCode) {
        return employeeService.existsByCode(employeeCode);
    }
}