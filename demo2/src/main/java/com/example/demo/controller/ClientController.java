package com.example.demo.controller;


import com.example.demo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientRepo clientRepo;

    @Autowired
    CoffeeRepo coffeeRepo;

    @GetMapping
    public String viewHome(Model model, HttpSession session) {

        List<Coffee>coffees = coffeeRepo.findall();

        model.addAttribute("coffees",coffees);


        List<Item>items = (List<Item>) session.getAttribute("item");
        model.addAttribute("items",items);
        return "index2";
    }



    @GetMapping("/loginpage")
    public String loginpage(){


        return "login";
    }

    @PostMapping("/login2")
    public String login(@Param("uname") String uname,@Param("pass") String pass,HttpSession session) {
        Client client = clientRepo.findClientByLoginPassword(uname, pass);
        if (client!= null) {

            System.out.println(uname+""+pass);
            session.setAttribute("client",client);
        }
        return "redirect:/";
    }

}