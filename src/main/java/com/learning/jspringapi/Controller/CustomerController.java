package com.learning.jspringapi.Controller;

import com.learning.jspringapi.Model.Branch;
import com.learning.jspringapi.Model.Customer;
import com.learning.jspringapi.Model.Industries;
import com.learning.jspringapi.Repository.CustomerRepository;
import com.learning.jspringapi.ResourceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> index(){
        return customerRepository.findAll();
    }


    @PostMapping("/customers/save")
    public Customer create(
            @Valid @RequestBody
            Customer customer
    ) {

        return customerRepository.save(customer);
    }

    @GetMapping("/customers/{customerId}")
    public Customer getSingle(@PathVariable(value = "customerId") Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));
    }

    //WOrked Perfectly
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> destroy(@PathVariable(value = "customerId") Long customerId) {
        Customer curr = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));
        customerRepository.delete(curr);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/customers/{customerId}")
    public Customer update(@PathVariable(value = "customerId") Long customerId,
                                 @RequestBody Customer customer) {
        Customer customer1 = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));

        customer1.setCustomername(customer1.getCustomername());
        customer1.setMnemonic(customer1.getMnemonic());
        customer1.setAddress(customer1.getAddress());
        customer1.setGender(customer1.getGender());
        customer1.setPhonenumber(customer1.getPhonenumber());
        customer1.setBranch(customer1.getBranch());
        customer1.setIndustries(customer1.getIndustries());
        customer1.setKinaddress(customer1.getKinaddress());
        customer1.setKinname(customer1.getKinname());
        customer1.setNationality(customer1.getNationality());

        Customer updatedTodo = customerRepository.save(customer1);
        return updatedTodo;
    }





}
