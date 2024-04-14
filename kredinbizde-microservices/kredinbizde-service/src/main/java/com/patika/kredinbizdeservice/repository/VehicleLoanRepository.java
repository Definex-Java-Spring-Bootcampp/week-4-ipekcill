package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.VehicleLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleLoanRepository extends JpaRepository<VehicleLoan, Long> {
}
