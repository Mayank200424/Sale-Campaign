package com.example.SaleCampaignManagement.Controller;

import com.example.SaleCampaignManagement.Model.Product;
import com.example.SaleCampaignManagement.Model.ResponseEntity;
import com.example.SaleCampaignManagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("save")
    ResponseEntity<List<Product>> saveProduct(@RequestBody List<Product> products){
        return productService.saveProduct(products);
    }

    @GetMapping("productsPage")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return productService.getProducts(page, pageSize);
    }

    @PostMapping("saveAll")
    public ResponseEntity<List<Product>> saveAllProduct(List<Product> products){
        return productService.saveAll(products);
    }



}
