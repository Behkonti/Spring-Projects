package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepo extends JpaRepository<Client,Integer>{

    @Query("select cl from Client cl where cl.login=?1 and cl.password=?2")
    Client findClientByLoginPassword(String login,String pass);
}
