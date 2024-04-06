package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
