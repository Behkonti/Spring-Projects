package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item,Integer> {

    @Query("select item from Item item")
    List<Item> findall();


    @Modifying
    @Query("update Item it set it.quantity =?1 where it.id=?2")
    void updateItemQuant( int quantity, int id);
}
