package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.HouseLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseLoanRepository extends JpaRepository<HouseLoan, Long> {
}
