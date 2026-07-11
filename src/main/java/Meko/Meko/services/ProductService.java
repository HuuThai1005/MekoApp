package Meko.Meko.services;

import Meko.Meko.entities.Product;
import Meko.Meko.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private Meko.Meko.repositories.CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          Meko.Meko.repositories.CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
    public Product findById(Integer id)
    {
        return productRepository.findById(id).orElse(null);

    }

    public List<Meko.Meko.entities.Category> findCategories() {
        return categoryRepository.findAll();
    }



}

