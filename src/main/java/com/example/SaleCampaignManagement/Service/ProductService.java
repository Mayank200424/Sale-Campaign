package com.example.SaleCampaignManagement.Service;

import com.example.SaleCampaignManagement.Model.PriceHistory;
import com.example.SaleCampaignManagement.Model.Product;
import com.example.SaleCampaignManagement.Model.ResponseEntity;
import com.example.SaleCampaignManagement.Repository.PriceHistoryRepository;
import com.example.SaleCampaignManagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    PriceHistoryRepository priceHistoryRepository;


    public ResponseEntity<List<Product>> saveProduct(List<Product> products){
        try{
            return new ResponseEntity<>(productRepository.saveAll(products), HttpStatus.OK,"Product Save SuccessFull");
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR,"Failed");
        }
    }

    public ResponseEntity<Page<Product>> getProducts(int page, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            return new ResponseEntity<>(productRepository.findAll(pageable), HttpStatus.OK, "Pagination SuccessFull");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "Failed");
        }
    }

    public void saveHistory(Product product, double price, LocalDate date,float discount,double discountPrice){
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setProduct(product);
        priceHistory.setPrice(price);
        priceHistory.setDate(date);
        priceHistory.setDiscount(discount);
        priceHistory.setDiscountPrice(discountPrice);
        priceHistoryRepository.save(priceHistory);
    }

    public ResponseEntity<List<Product>> saveAll(List<Product> products){
        try{
            List<Product> productList = productRepository.saveAll(products);
            for (Product product : productList){
                double discountAmount = product.getCurrentPrice() * (product.getDiscount() / 100);
                saveHistory(product,product.getCurrentPrice(),LocalDate.now(),product.getDiscount(),discountAmount);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK,"SuccessFull");
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR,"Failed");
        }
    }
}
