package com.example.demo;


import com.example.demo.model.OrderClient;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "orders", cascade =  {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();


    @Column(name = "localdate")
    private LocalDateTime localdate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_client_id")
    private OrderClient orderClient;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }



    public LocalDateTime getLocaldate() {
        return localdate;
    }

    public void setLocaldate(LocalDateTime localdate) {
        this.localdate = localdate;
    }

    public OrderClient getOrderClient() {
        return orderClient;
    }

    public void setOrderClient(OrderClient orderClient) {
        this.orderClient = orderClient;
    }


}
