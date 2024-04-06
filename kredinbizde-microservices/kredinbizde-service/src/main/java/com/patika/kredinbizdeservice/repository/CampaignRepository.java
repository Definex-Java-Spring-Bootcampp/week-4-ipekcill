package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query("SELECT c FROM Campaign c ORDER BY c.startingDate DESC")
    List<Campaign> findAllOrderByStartDateDesc();

}
