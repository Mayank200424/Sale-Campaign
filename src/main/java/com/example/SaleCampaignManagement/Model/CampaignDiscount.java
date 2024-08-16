package com.example.SaleCampaignManagement.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "campaign_discounts")
public class CampaignDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaignDiscount_id")
    private int campaignDiscountId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "discount")
    private float discount;

    public int getCampaignDiscountId() {
        return campaignDiscountId;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

}
