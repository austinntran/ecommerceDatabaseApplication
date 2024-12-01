package org.example.ecomm.repository;

import org.example.ecomm.model.ListedItem;
import org.example.ecomm.model.ListedItemWishlist;
import org.example.ecomm.model.User;
import org.example.ecomm.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.example.ecomm.model.ListedItemWishlist;
import org.example.ecomm.model.ListedItemWishlistId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListedItemWishlistRepository extends JpaRepository<ListedItemWishlist, ListedItemWishlistId> {
    Optional<ListedItemWishlist> findByWishlist(Wishlist wishlist);
}

