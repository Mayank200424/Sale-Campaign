package com.example.SaleCampaignManagement.Controller;

import com.example.SaleCampaignManagement.Model.Campaign;
import com.example.SaleCampaignManagement.Model.ResponseEntity;
import com.example.SaleCampaignManagement.Service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("campaigns")
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @PostMapping("saveCampaign")
    public ResponseEntity<Campaign> saveCampaign(@RequestBody Campaign campaign) {
        return campaignService.saveCampaign(campaign);
    }
}
