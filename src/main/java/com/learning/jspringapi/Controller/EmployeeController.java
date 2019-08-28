package com.learning.jspringapi.Controller;

import com.learning.jspringapi.Model.Employee;
import com.learning.jspringapi.Repository.EmployeeRepository;
import com.learning.jspringapi.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;

    }

    @RequestMapping(value = "employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAll(){
        return employeeService.index();
    }

    @RequestMapping(value = "employees",method=RequestMethod.POST,produces=APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> create(@RequestBody Employee employee) throws URISyntaxException {
        try {
            Employee result = employeeService.save(employee);
            return ResponseEntity.created(new
                    URI("/api/v1/employees/"+result.getId())).body(result);
        }catch (Exception e){
            return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "employees",method=RequestMethod.PUT,produces=APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> update(@RequestBody Employee employee) throws URISyntaxException {
        if(employee.getId() == null){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        try{
            Employee result = employeeService.update( employee);
            return ResponseEntity.created(new
                    URI("/api/v1/employees/"+result.getId())).body(result);
        }catch (Exception e){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "employees",method=RequestMethod.DELETE,produces=APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        employeeService.delete(id);
        return ResponseEntity.ok().build();

    }
}
