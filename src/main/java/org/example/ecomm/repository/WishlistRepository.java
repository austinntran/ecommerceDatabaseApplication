package org.example.ecomm.repository;

import org.example.ecomm.model.User;
import org.example.ecomm.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findByIdContaining(int wishlist_id);
    @Procedure(name = "AddWishlist")
    void AddWishlist(@Param("@wishlist_id") int wishlist_id,
                 @Param("@user") User user
    );

}
