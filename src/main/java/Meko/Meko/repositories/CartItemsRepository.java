package Meko.Meko.repositories;

import Meko.Meko.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
    CartItems findByCartIdAndProductId(
            Integer cartId,
            Integer productId);
}
