package com.example.demo.model;


import com.example.demo.Client;
import com.example.demo.Orders;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "orderyear")
    private Integer orderyear;

    @Column(name = "ordermonth")
    private Integer ordermonth;

    @Column(name = "orderday")
    private Integer orderday;



    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "orderClient", cascade = {CascadeType.MERGE,CascadeType.PERSIST}, orphanRemoval = true)
    private List<Orders> orderses = new ArrayList<>();

    @Column(name = "ldate", unique = true)
    private LocalDate ldate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrderyear() {
        return orderyear;
    }

    public void setOrderyear(Integer orderyear) {
        this.orderyear = orderyear;
    }

    public Integer getOrdermonth() {
        return ordermonth;
    }

    public void setOrdermonth(Integer ordermonth) {
        this.ordermonth = ordermonth;
    }

    public Integer getOrderday() {
        return orderday;
    }

    public void setOrderday(Integer orderday) {
        this.orderday = orderday;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Orders> getOrderses() {
        return orderses;
    }

    public void setOrderses(List<Orders> orderses) {
        this.orderses = orderses;
    }

    public LocalDate getLdate() {
        return ldate;
    }

    public void setLdate(LocalDate ldate) {
        this.ldate = ldate;
    }

}
