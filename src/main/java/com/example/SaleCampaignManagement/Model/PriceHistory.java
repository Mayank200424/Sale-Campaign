package com.example.SaleCampaignManagement.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "price_history")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "price_history_id")
    private int priceHistoryId;

    @Column(name = "price")
    private double price;

    @Column(name = "discount_Price")
    private double discountPrice;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "discount")
    private float discount;


    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
         this.discount = discount;
    }

    public int getPriceHistoryId() {
        return priceHistoryId;
    }

    public void setPriceHistoryId(int priceHistoryId) {
        this.priceHistoryId = priceHistoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
