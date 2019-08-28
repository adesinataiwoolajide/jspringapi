package com.learning.jspringapi.Repository;

import com.learning.jspringapi.Model.AccountOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountOfficerRepository extends JpaRepository<AccountOfficer, Long> {
}
