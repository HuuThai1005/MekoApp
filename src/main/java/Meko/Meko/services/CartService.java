package Meko.Meko.services;

import Meko.Meko.entities.Cart;
import Meko.Meko.entities.CartItems;
import Meko.Meko.entities.Product;
import Meko.Meko.entities.User;
import Meko.Meko.repositories.CartItemsRepository;
import Meko.Meko.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartService {

    private final CartItemsRepository cartItemsRepository;
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository, CartItemsRepository cartItemsRepository) {
        this.cartRepository = cartRepository;
        this.cartItemsRepository = cartItemsRepository;
    }

    public void addProduct(
            User user,
            Product product){

        Cart cart =
                cartRepository.findByUser(user);

        if(cart == null){

            cart = new Cart();

            cart.setUser(user);

            cartRepository.save(cart);
        }

        CartItems existingItem =
                cartItemsRepository
                        .findByCartIdAndProductId(
                                cart.getId(),
                                product.getId());

        if(existingItem != null){

            existingItem.setQuantity(
                    existingItem.getQuantity() + 1);

            cartItemsRepository.save(existingItem);

            return;
        }

        CartItems item =
                new CartItems();

        item.setCart(cart);

        item.setProduct(product);

        item.setQuantity(1);

        item.setUnitPrice(product.getPrice());

        cartItemsRepository.save(item);
    }

    public Cart findByUser(User user){

        return cartRepository.findByUser(user);
    }

    public void updateQuantity(
            User user,
            Integer productId,
            String action){

        Cart cart =
                cartRepository.findByUser(user);

        CartItems item =
                cartItemsRepository
                        .findByCartIdAndProductId(
                                cart.getId(),
                                productId);

        if(item == null){
            return;
        }

        if(action.equals("increase")){

            item.setQuantity(
                    item.getQuantity() + 1);
        }

        if(action.equals("decrease")){

            int qty =
                    item.getQuantity() - 1;

            if(qty <= 0){

                cartItemsRepository.delete(item);

                return;
            }

            item.setQuantity(qty);
        }

        cartItemsRepository.save(item);
    }

    public void deleteItem(
            User user,
            Integer productId){

        Cart cart =
                cartRepository.findByUser(user);

        CartItems item =
                cartItemsRepository
                        .findByCartIdAndProductId(
                                cart.getId(),
                                productId);

        if(item != null){

            cartItemsRepository.delete(item);
        }
    }

    public Cart save(Cart cart){

        return cartRepository.save(cart);
    }
}
