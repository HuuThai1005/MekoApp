package Meko.Meko.repositories;

import Meko.Meko.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer >
{

}
