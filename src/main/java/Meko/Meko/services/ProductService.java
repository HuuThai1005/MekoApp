package Meko.Meko.services;

import Meko.Meko.entities.Product;
import Meko.Meko.repositories.ProductRepository;
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
    public Product save(Product product)
    {
        return productRepository.save(product);
    }
    public void delete(Integer id){
        productRepository.deleteById(id);
    }

}

