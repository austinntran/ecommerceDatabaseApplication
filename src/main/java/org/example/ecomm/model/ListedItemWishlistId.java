package org.example.ecomm.model;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ListedItemWishlistId implements Serializable {

    private Long wishlistId;
    private Long listedItemId;

    public ListedItemWishlistId() {}

    public ListedItemWishlistId(Long wishlistId, Long listedItemId) {
        this.wishlistId = wishlistId;
        this.listedItemId = listedItemId;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
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
        ListedItemWishlistId that = (ListedItemWishlistId) o;
        return Objects.equals(wishlistId, that.wishlistId) &&
                Objects.equals(listedItemId, that.listedItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishlistId, listedItemId);
    }
}
