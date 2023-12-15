package com.example.demo;


import jakarta.persistence.*;



@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coffeeid", nullable = false)
    private int coffeeid;

    @Column(name = "cofname")
    private String cofname;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;



    public int getCoffeeid() {
        return coffeeid;
    }

    public void setCoffeeid(int coffeeid) {
        this.coffeeid = coffeeid;
    }

    public String getCofname() {
        return cofname;
    }

    public void setCofname(String cofname) {
        this.cofname = cofname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
