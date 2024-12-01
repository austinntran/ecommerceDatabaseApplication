package org.example.ecomm.repository;

import org.example.ecomm.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListedItemShoppingCartRepository extends JpaRepository<ListedItemShoppingCart, ListedItemShoppingCartId> {
    Optional<ListedItemShoppingCart> findByShoppingCart(ShoppingCart shoppingCart);
}

