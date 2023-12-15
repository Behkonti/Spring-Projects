package com.example.demo;

import com.example.demo.model.OrderClient;
import com.example.demo.service.Orderservice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
@Transactional
public class Demo2Application implements CommandLineRunner {
    @Autowired
    CoffeeRepo coffeeRepo;

    @Autowired
    ItemRepo itemRepo;


    @Autowired
    ClientRepo clientRepo;



    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    Orderservice orderservice;
    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Orders> orders = ordersRepo.findAll(Sort.by("localdate"));
        for (Orders orders1 : orders) {
            System.out.println("Orderid:" + orders1.getId() + "Orderdate:" + orders1.getLocaldate().toString());
        }
        //-------------------------------
        Client client = clientRepo.getById(1);
        OrderClient orderClient = ordersRepo.getOrderfClient(client.getId(), 2023, 11, 9);
        if (orderClient != null) {
             orderservice.orderbyyears(client);
            System.out.println(orderClient.getClient().getId() + "Ordermonth:" + orderClient.getOrdermonth() + "Orderdy:" + orderClient.getOrderday());
        }else {

        }
    }
}
