package org.example.ecomm.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;


@Entity
@Table(name = "Car")
@Check(constraints = "[year] > 1900")
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long carId;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "[year]")
    private int year;

    @Column(name = "color")
    private String color;

    public Car() {}

    public Car(long carId, User user, String make, String model, int year, String color) {
        this.carId = carId;
        this.user = user;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

