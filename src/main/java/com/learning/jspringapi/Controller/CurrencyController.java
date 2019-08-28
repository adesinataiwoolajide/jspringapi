package com.learning.jspringapi.Controller;

import com.learning.jspringapi.Model.Currencies;
import com.learning.jspringapi.Repository.CurrenciesRepository;
import com.learning.jspringapi.ResourceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class CurrencyController {

    @Autowired
    CurrenciesRepository currenciesRepository;

    @GetMapping("/currencies")
    public List<Currencies> index(){
        return currenciesRepository.findAll();
    }

    @PostMapping("/currencies/save")
    public Currencies create(@Valid @RequestBody Currencies save) {
        return currenciesRepository.save(save);
    }

    @GetMapping("/currencies/{currencyId}")
    public Currencies getSingleTodo(@PathVariable(value = "currencyId") Long currencyId) {
        return currenciesRepository.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currencies", "currencyId", currencyId));
    }

    //WOrked Perfectly
    @DeleteMapping("/currencies/{currencyId}")
    public ResponseEntity<?> destroy(@PathVariable(value = "currencyId") Long currencyId) {
        Currencies curr = currenciesRepository.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currencies", "currencyId", currencyId));
        currenciesRepository.delete(curr);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/currencies/{currencyId}")
    public Currencies update(@PathVariable(value = "currencyId") Long currencyId,
                        @RequestBody Currencies currencies) {
        Currencies currencies1 = currenciesRepository.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currencies", "currencyId", currencyId));

        currencies1.setCurrencyName(currencies1.getCurrencyName());
        Currencies updatedTodo = currenciesRepository.save(currencies1);
        return updatedTodo;
    }
}
