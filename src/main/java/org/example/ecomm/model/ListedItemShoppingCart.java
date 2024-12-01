package org.example.ecomm.model;

import jakarta.persistence.*;

@Entity
@Table(name="ListedItem_ShoppingCart")
public class ListedItemShoppingCart {

    @EmbeddedId
    private ListedItemShoppingCartId id;

    @ManyToOne
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shoppingcart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @MapsId("listedItemId")
    @JoinColumn(name = "item_id")
    private ListedItem listedItem;

    public ListedItemShoppingCartId getId() {
        return id;
    }

    public void setId(ListedItemShoppingCartId id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ListedItem getListedItem() {
        return listedItem;
    }

    public void setListedItem(ListedItem listedItem) {
        this.listedItem = listedItem;
    }
}
