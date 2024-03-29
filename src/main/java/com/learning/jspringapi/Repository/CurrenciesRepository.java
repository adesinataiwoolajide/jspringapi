package com.learning.jspringapi.Repository;

import com.learning.jspringapi.Model.Currencies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesRepository extends JpaRepository<Currencies, Long> {
}
