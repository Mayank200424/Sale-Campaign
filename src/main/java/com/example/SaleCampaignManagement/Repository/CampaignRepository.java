package com.example.SaleCampaignManagement.Repository;

import com.example.SaleCampaignManagement.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Integer> {

    @Query(value = "select * from campaigns where start_date = :startDate", nativeQuery = true)
    List<Campaign> findAllByStartDate(@Param("startDate") LocalDate startDate);

    @Query(value="select * from campaigns where end_date = :endDate",nativeQuery = true)
    List<Campaign> findAllByEndDate(@Param("endDate") LocalDate endDate);


}
