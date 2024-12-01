package org.example.ecomm.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ListedItemShoppingCartId implements Serializable {

    private Long shoppingCartId;
    private Long listedItemId;

    public ListedItemShoppingCartId() {}

    public ListedItemShoppingCartId(Long shoppingCartId, Long listedItemId) {
        this.shoppingCartId = shoppingCartId;
        this.listedItemId = listedItemId;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getListedItemId() {
        return listedItemId;
    }

    public void setListedItemId(Long listedItemId) {
        this.listedItemId = listedItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListedItemShoppingCartId that = (ListedItemShoppingCartId) o;
        return Objects.equals(shoppingCartId, that.shoppingCartId) &&
                Objects.equals(listedItemId, that.listedItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCartId, listedItemId);
    }
}
