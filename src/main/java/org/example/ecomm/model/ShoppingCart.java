package org.example.ecomm.model;
import jakarta.persistence.*;

@Entity
@Table(name="ShoppingCart")
public class ShoppingCart {

    @Id
    @Column(name = "shoppingcart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shoppingcart_id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public ShoppingCart(){}

    public ShoppingCart(long shoppingcart_id, User user) {
        this.shoppingcart_id = shoppingcart_id;
        this.user = user;
    }

    public long getShoppingcart_id() {
        return shoppingcart_id;
    }

    public void setShoppingcart_id(long shoppingcart_id) {
        this.shoppingcart_id = shoppingcart_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
