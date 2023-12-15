package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoffeeRepo extends JpaRepository<Coffee,Integer> {

    @Query("select cof from Coffee cof")
    List<Coffee> findall();

    @Query("select cof from Coffee cof where cof.price>=?1 and cof.price<=?2")
    List<Coffee> findByPrice(double price1,double price2);

    @Query("select cof from Coffee cof")
    public Page<Coffee> getAllCoffeesPage(Pageable pageable);
}
