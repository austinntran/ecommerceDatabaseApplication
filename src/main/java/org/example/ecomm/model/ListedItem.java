package org.example.ecomm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(name = "ListedItem")
public class ListedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long itemId;

    @Column(name = "seller_username")
    private String sellerUsername;

    @Column(name = "buyer_username")
    private String buyerUsername;

    @Column(name = "[description]")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "[name]")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "list_date")
    @CreationTimestamp
    private Date listDate;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "condition_id")
    private int conditionId;

    public ListedItem() {}

    public ListedItem(long itemId, String sellerUsername, String buyerUsername, String description, int quantity, String name, int price, Date listDate, Date purchaseDate, int conditionId) {
        this.itemId = itemId;
        this.sellerUsername = sellerUsername;
        this.buyerUsername = buyerUsername;
        this.description = description;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.listDate = listDate;
        this.purchaseDate = purchaseDate;
        this.conditionId = conditionId;
    }

    public ListedItem(long itemId, String sellerUsername, String description, int quantity, String name, int price, Date listDate, int conditionId) {
        this.itemId = itemId;
        this.sellerUsername = sellerUsername;
        this.description = description;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.listDate = listDate;
        this.conditionId = conditionId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getListDate() {
        return listDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }
}
