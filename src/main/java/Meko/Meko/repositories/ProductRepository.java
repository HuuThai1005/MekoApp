package Meko.Meko.repositories;

import Meko.Meko.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer >
{
    List<Product> findByProductNameContainingIgnoreCase(String keyword);
    List<Product> findByCategoryIdAndProductNameContainingIgnoreCase(Integer categoryId, String keyword);


}
