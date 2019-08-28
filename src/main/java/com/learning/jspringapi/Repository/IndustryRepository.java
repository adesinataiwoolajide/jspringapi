package com.learning.jspringapi.Repository;

import com.learning.jspringapi.Model.Industries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Industries, Long> {
}
