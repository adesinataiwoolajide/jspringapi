package com.learning.jspringapi.Controller;


import com.learning.jspringapi.Model.AccountOfficer;
import com.learning.jspringapi.Repository.AccountOfficerRepository;
import com.learning.jspringapi.ResourceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountOfficerController {

    @Autowired
    AccountOfficerRepository accountOfficerRepository;

    @GetMapping("/account_officer")
    public List<AccountOfficer> index(){
        return accountOfficerRepository.findAll();
    }

    @PostMapping("/account_officer/save")
    public AccountOfficer create(@Valid @RequestBody AccountOfficer save) {
        return accountOfficerRepository.save(save);
    }

    @GetMapping("/account_officer/{officerId}")
    public AccountOfficer getSingle(@PathVariable(value = "officerId") Long officerId) {
        return accountOfficerRepository.findById(officerId)
                .orElseThrow(() -> new ResourceNotFoundException("AccountOfficer", "officerId", officerId));
    }

    //WOrked Perfectly
    @DeleteMapping("/account_officer/{officerId}")
    public ResponseEntity<?> destroy(@PathVariable(value = "officerId") Long officerId) {
        AccountOfficer curr = accountOfficerRepository.findById(officerId)
                .orElseThrow(() -> new ResourceNotFoundException("AccountOfficer", "officerId", officerId));
        accountOfficerRepository.delete(curr);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/account_officer/{officerId}")
    public AccountOfficer update(@PathVariable(value = "officerId") Long officerId,
                         @RequestBody AccountOfficer accountOfficer) {
        AccountOfficer currencies1 = accountOfficerRepository.findById(officerId)
                .orElseThrow(() -> new ResourceNotFoundException("AccountOfficer", "officerId", officerId));

        currencies1.setOfficerName(currencies1.getOfficerName());
        AccountOfficer updatedTodo = accountOfficerRepository.save(currencies1);
        return updatedTodo;
    }
}
