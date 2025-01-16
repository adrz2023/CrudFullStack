package com.example.RunDemo.controller;

import com.example.RunDemo.Entity.NewOrder;
import com.example.RunDemo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")


public class orderTest {

    @Autowired

    private OrderService orderService;



    @PostMapping("/{personId}")

    public NewOrder bookOrder(@RequestBody NewOrder newOrder, @PathVariable int personId){

        return orderService.createOrder(newOrder,personId);
    }





}
