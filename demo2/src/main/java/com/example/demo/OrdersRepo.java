package com.example.demo;


import com.example.demo.model.OrderClient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders,Integer> {

    @Query("select o from Orders o")
    List<Orders> findall();

    @Query("select item from Item item where item.orders.id=?1")
    List<Item> findItemByOrder(int orderid);



    @Query("select o from Orders o where year(o.localdate)=?1 and month(o.localdate)=?2 and day(o.localdate)=?3 and o.client.id=?4")
    List<Orders> getOrder(int year,int month,int day,int clientid);

    @Query("select o from Orders o")
    public Page<Orders> getAllOrdersPage(Pageable pageable);

    @Query("select o from OrderClient o order by o.orderyear,o.ordermonth,o.orderday")
    List<OrderClient> getOrdersofClient();

    @Query("select o from OrderClient o where o.client.id=?1 and o.orderyear=?2 and o.ordermonth=?3 and o.orderday=?4")
    OrderClient getOrderfClient(int clientid,int orderyear,int ordermonth,int orderday);

}
