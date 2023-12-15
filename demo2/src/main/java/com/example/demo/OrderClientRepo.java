package com.example.demo;

import com.example.demo.model.OrderClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderClientRepo extends JpaRepository<OrderClient,Integer> {


}
