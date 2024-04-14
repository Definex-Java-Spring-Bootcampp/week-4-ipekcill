package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.ConsumerLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerLoanRepository extends JpaRepository<ConsumerLoan, Long> {
}
