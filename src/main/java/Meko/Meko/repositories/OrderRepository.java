package Meko.Meko.repositories;

import Meko.Meko.entities.Orders;
import Meko.Meko.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByUserOrderByCreatedAtDesc(User user);
}
