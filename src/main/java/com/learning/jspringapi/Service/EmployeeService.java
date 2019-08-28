package com.learning.jspringapi.Service;

import com.learning.jspringapi.Model.Employee;
import com.learning.jspringapi.Repository.EmployeeRepository;
import com.learning.jspringapi.ResourceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        if(employee.getId() != null && employeeRepository.existsById(employee.getId())){
            throw new EntityExistsException("The ID Already exist in the database");
        }
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee){

        if(employee.getId() !=null && employeeRepository.existsById(employee.getId())){
            throw new EntityExistsException("The ID Already exist in the database");
        }return employeeRepository.save(employee);

    }

    public List<Employee> index(){
        return employeeRepository.findAll();
    }

    public Employee findOne(Integer id){
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "id", id));
    }

    public Employee delete(Integer id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
        return employee;
    }

}
