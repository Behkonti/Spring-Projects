package com.example.demo.service;

import com.example.demo.*;
import com.example.demo.model.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service

public class Orderservice {

    OrdersRepo ordersRepo;
    ClientRepo clientRepo;

    OrderClientRepo orderClientRepo;

    @Autowired
    public Orderservice(OrdersRepo ordersRepo, ClientRepo clientRepo, OrderClientRepo orderClientRepo) {
        this.ordersRepo = ordersRepo;
        this.clientRepo = clientRepo;
        this.orderClientRepo = orderClientRepo;
    }

    public void orderbyyears(Client client) {
        Client client1 = clientRepo.getById(client.getId());
        int x=1;

        System.out.println("Ordercliient null");

        for (int year = 2023; year > 2021; year--) {

            for (int month = 1; month <= 12; month++) {
                for(int day=1;day<=31;day++) {
                    List<Orders> ordersList2 = ordersRepo.getOrder(year, month, day, client1.getId());
                    OrderClient orderClient = ordersRepo.getOrderfClient(client1.getId(), year, month, day);

                    if (ordersList2.size() == 0) {

                    } else if (ordersList2.size() > 0) {
                        System.out.println("Year:" + year + "month:"+month);
                            if( orderClient==null){
                                System.out.println("Ordercliient null");
                                OrderClient orderClient1 = new OrderClient();

                                orderClient1.setClient(client1);
                                orderClient1.setOrderyear(year);
                                orderClient1.setOrdermonth(month);
                                orderClient1.setOrderday(day);
                                orderClient1.setOrderses(ordersList2);
                                orderClientRepo.save(orderClient1);
                            }else {
                                for(Orders orders:ordersList2){
                                    orders.setOrderClient(orderClient);
                                    ordersRepo.save(orders);
                                  orderClient.getOrderses().add(orders);
                                }
                                orderClientRepo.save(orderClient);

                            }
                        for (int i = 0; i < ordersList2.size(); i++) {
                            System.out.println("" + ordersList2.get(i).getLocaldate().toString());
                        }
                    }
                }

            }
        }
    }
}