package com.example.SaleCampaignManagement.Service;

import com.example.SaleCampaignManagement.Model.Campaign;
import com.example.SaleCampaignManagement.Model.CampaignDiscount;
import com.example.SaleCampaignManagement.Model.ResponseEntity;
import com.example.SaleCampaignManagement.Repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {
    @Autowired
    CampaignRepository campaignRepository;

    public ResponseEntity<Campaign> saveCampaign(Campaign campaign){
        try {
            for (CampaignDiscount discount : campaign.getCampaignDiscounts()) {
                discount.setCampaign(campaign);
            }
            return new ResponseEntity<>(campaignRepository.save(campaign), HttpStatus.OK, "save campaign successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "failed to save " + e.getMessage());
        }
    }
}
