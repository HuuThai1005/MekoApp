package Meko.Meko.repositories;

import Meko.Meko.entities.Cart;
import Meko.Meko.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}
