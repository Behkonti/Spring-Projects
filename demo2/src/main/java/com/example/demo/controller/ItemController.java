package com.example.demo.controller;


import com.example.demo.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {


    @Autowired
    ItemRepo itemRepo;

    @Autowired
    CoffeeRepo coffeeRepo;
    @GetMapping("/additem/{id}")
    public String additem(@PathVariable(value = "id")int id, Model model, HttpServletRequest request, @ModelAttribute("client") Client client1){
        Coffee coffee = coffeeRepo.findById(id).get();
        Client client = (Client) request.getSession().getAttribute("client");
        HttpSession session = request.getSession();
        if(client==null) {
            return "redirect:/";
        }else {

             List<Item>items = (List<Item>) session.getAttribute("item");
            if (items == null) {
items = new ArrayList<>();

                Item item = new Item();
                item.setId(id);
                item.setCoffee(coffee);
                item.setQuantity(1);
                item.setProdimage(coffee.getImage());
                items.add(item);
                session.setAttribute("item", items);
            } else {
                int x=0;
                List<Item>items2 = (ArrayList<Item>) session.getAttribute("item");
                for (int i=0;i<items2.size();i++) {
                    if (items2.get(i).getCoffee().getCoffeeid() == coffee.getCoffeeid()) {
                        x=1;
                       // return "redirect:/";
                    }

                    //else {
                    //    Item item2 = new Item();
                    //    item2.setCoffee(coffee);
                    //    item2.setQuantity(1);
                    //    items.add(item2);
                    //}
                }
                if(x==1){
                    return "redirect:/";
                }else {
                    Item item2 = new Item();
                        item2.setCoffee(coffee);
                        item2.setQuantity(1);
                        item2.setProdimage(coffee.getImage());
                        items.add(item2);
                    session.setAttribute("item", items);
                }
            }
        }
        return "redirect:/";
    }
}
