package com.learning.jspringapi.Controller;

import com.learning.jspringapi.Model.Industries;
import com.learning.jspringapi.Repository.IndustryRepository;
import com.learning.jspringapi.ResourceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class IndustryController {

    @Autowired
    IndustryRepository industryRepository;

    @GetMapping("/industries")
    public List<Industries> index(){
        return industryRepository.findAll();
    }

    @PostMapping("/industries/save")
    public Industries create(@Valid @RequestBody Industries indostry) {

        return industryRepository.save(indostry);
    }

    @GetMapping("/industries/{industryId}")
    public Industries getSingle(@PathVariable(value = "industryId") Long industryId) {
        return industryRepository.findById(industryId)
                .orElseThrow(() -> new ResourceNotFoundException("Industries", "industryId", industryId));
    }

    //WOrked Perfectly
    @DeleteMapping("/industries/{industryId}")
    public ResponseEntity<?> destroy(@PathVariable(value = "industryId") Long industryId) {
        Industries curr = industryRepository.findById(industryId)
                .orElseThrow(() -> new ResourceNotFoundException("Industries", "industryId", industryId));
        industryRepository.delete(curr);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/industries/{industryId}")
    public Industries update(@PathVariable(value = "industryId") Long industryId,
                             @RequestBody Industries industries) {
        Industries currencies1 = industryRepository.findById(industryId)
                .orElseThrow(() -> new ResourceNotFoundException("Currencies", "currencyId", industryId));

        currencies1.setIndustryName(currencies1.getIndustryName());
        Industries updatedTodo = industryRepository.save(currencies1);
        return updatedTodo;
    }

}
