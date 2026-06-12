package Meko.MekoApp.services;

import Meko.MekoApp.entities.Product;
import Meko.MekoApp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        products = productRepository.findAll();
        return products;

    }
}

