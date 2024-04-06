package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}

