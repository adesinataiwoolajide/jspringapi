package com.learning.jspringapi.Controller;

import com.learning.jspringapi.Model.Branch;
import com.learning.jspringapi.Repository.BranchRepository;
import com.learning.jspringapi.Repository.CurrenciesRepository;
import com.learning.jspringapi.ResourceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BranchController {

    @Autowired
    BranchRepository branchRepository;

    @GetMapping("/branches")
    public List<Branch> index(){
        return branchRepository.findAll();
    }

    @PostMapping("/branches/save")
    public Branch create(@Valid @RequestBody Branch save) {
        return branchRepository.save(save);
    }

    @GetMapping("/branches/{branchId}")
    public Branch getSingle(@PathVariable(value = "branchId") Long branchId) {
        return branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "branchId", branchId));
    }

    //WOrked Perfectly
    @DeleteMapping("/branches/{branchId}")
    public ResponseEntity<?> destroy(@PathVariable(value = "branchId") Long branchId) {
        Branch curr = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "branchId", branchId));
        branchRepository.delete(curr);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/branches/{branchId}")
    public Branch update(@PathVariable(value = "branchId") Long branchId,
                             @RequestBody Branch branch) {
        Branch currencies1 = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "branchId", branchId));

        currencies1.setBranchName(currencies1.getBranchName());
        Branch updatedTodo = branchRepository.save(currencies1);
        return updatedTodo;
    }
}
