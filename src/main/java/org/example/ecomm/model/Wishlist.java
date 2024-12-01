package org.example.ecomm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="Wishlist")
public class Wishlist {

    @Id
    @Column(name = "wishlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wishlist_id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public Wishlist(){}

    public Wishlist(long id, User user) {
        wishlist_id = id;
        this.user = user;
    }

    public long getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(long wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wishlist wishlist = (Wishlist) o;
        return wishlist_id == wishlist.wishlist_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishlist_id);
    }
}
