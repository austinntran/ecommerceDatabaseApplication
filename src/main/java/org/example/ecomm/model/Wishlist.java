package org.example.ecomm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="[Wishlist]")
public class Wishlist {

    @Id
    @Column(name = "wishlist_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wishlist_id;

    @ManyToOne
    @JoinColumn(name = "User_username", referencedColumnName = "username")
    private User user;

    public Wishlist(){}

    public Wishlist(int id, User user) {
        wishlist_id = id;
        this.user = user;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
