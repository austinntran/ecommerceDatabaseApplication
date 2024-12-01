package org.example.ecomm.model;

import jakarta.persistence.*;

@Entity
public class ListedItemWishlist {

    @EmbeddedId
    private ListedItemWishlistId id;

    @ManyToOne
    @MapsId("wishlistId")
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    @ManyToOne
    @MapsId("listedItemId")
    @JoinColumn(name = "listedItem_id")
    private ListedItem listedItem;

    public ListedItemWishlistId getId() {
        return id;
    }

    public void setId(ListedItemWishlistId id) {
        this.id = id;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public ListedItem getListedItem() {
        return listedItem;
    }

    public void setListedItem(ListedItem listedItem) {
        this.listedItem = listedItem;
    }
}
