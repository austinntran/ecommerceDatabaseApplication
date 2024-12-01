package org.example.ecomm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "[User]")
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "passhash")
    private String passwordHash;

    @Column(name = "EncryptedPasswordHash")
    private byte[] encryptedPasswordHash;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "isbuyer")
    private boolean isBuyer;

    @Column(name = "isseller")
    private boolean isSeller;

    public User() {}

    public User(String username, String passwordHash, byte[] encryptedPasswordHash, String fullName, boolean isBuyer, boolean isSeller) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.encryptedPasswordHash = encryptedPasswordHash;
        this.fullName = fullName;
        this.isBuyer = isBuyer;
        this.isSeller = isSeller;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getEncryptedPasswordHash() {
        return encryptedPasswordHash;
    }

    public void setEncryptedPasswordHash(byte[] encryptedPasswordHash) {
        this.encryptedPasswordHash = encryptedPasswordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isBuyer() {
        return isBuyer;
    }

    public void setBuyer(boolean buyer) {
        isBuyer = buyer;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }
}
