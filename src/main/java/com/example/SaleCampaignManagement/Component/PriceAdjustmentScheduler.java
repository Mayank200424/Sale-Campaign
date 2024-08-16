package com.example.SaleCampaignManagement.Component;

import com.example.SaleCampaignManagement.Model.Campaign;
import com.example.SaleCampaignManagement.Model.CampaignDiscount;
import com.example.SaleCampaignManagement.Model.PriceHistory;
import com.example.SaleCampaignManagement.Model.Product;
import com.example.SaleCampaignManagement.Repository.CampaignRepository;
import com.example.SaleCampaignManagement.Repository.PriceHistoryRepository;
import com.example.SaleCampaignManagement.Repository.ProductRepository;
import com.example.SaleCampaignManagement.Service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class PriceAdjustmentScheduler {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    PriceHistoryRepository priceHistoryRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void activeSale() {
        LocalDate today = LocalDate.now();
        List<Campaign> activeSales = campaignRepository.findAllByStartDate(today);
        System.out.println(activeSales);
        for (Campaign campaign : activeSales) {
            List<CampaignDiscount> discounts = campaign.getCampaignDiscounts();
            System.out.println(campaign);
            for (CampaignDiscount discount : discounts) {
                Product product = productRepository.findById(discount.getProductId()).orElse(null);
                System.out.println(product);
                if (product != null) {
                    float discountAmount = product.getCurrentPrice() * (discount.getDiscount() / 100);
                    long newPrice = (long) (product.getCurrentPrice() - discountAmount);
                    if (newPrice >= 0) {
                        product.setCurrentPrice(newPrice);
                        product.setDiscount(discount.getDiscount());
                        productRepository.save(product);
                        productService.saveHistory(product, newPrice, LocalDate.now(),product.getDiscount(),discountAmount);
                    }
                }
            }
        }
    }

    @Scheduled(cron = "59 59 23 * * *")
    @Transactional
    public void endSale() {
        LocalDate today = LocalDate.now();
        List<Campaign> activeSales = campaignRepository.findAllByEndDate(today);
        System.out.println(activeSales);
        for (Campaign campaign : activeSales) {
            List<CampaignDiscount> discounts = campaign.getCampaignDiscounts();
            System.out.println(campaign);
            for (CampaignDiscount discount : discounts) {
                Product product = productRepository.findById(discount.getProductId()).orElse(null);
                System.out.println(product);
                if (product != null) {
                    LocalDate date = campaign.getStartDate();
                    PriceHistory priceHistory = priceHistoryRepository.findTopByProductIdAndDate(product.getProductId(), date);
                    if (priceHistory != null) {
                        double previousPrice = priceHistory.getDiscountPrice();
                        product.setCurrentPrice((long) (priceHistory.getPrice() + previousPrice));
                        product.setDiscount(0);
                        productRepository.save(product);
                        productService.saveHistory(product, priceHistory.getPrice() + previousPrice, LocalDate.now(),product.getDiscount(),priceHistory.getDiscountPrice());
                    }
                }
            }
        }
    }
}
