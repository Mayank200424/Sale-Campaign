package com.example.SaleCampaignManagement.Repository;

import com.example.SaleCampaignManagement.Model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistory,Integer> {


   @Query(value = "SELECT * FROM price_history WHERE product_id = :productId AND date = :date ORDER BY price_history_id DESC LIMIT 1", nativeQuery = true)
   PriceHistory findTopByProductIdAndDate(@Param("productId") int productId, @Param("date") LocalDate date);


}
