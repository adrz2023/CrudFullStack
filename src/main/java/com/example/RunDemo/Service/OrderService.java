package com.example.RunDemo.Service;

import com.example.RunDemo.Entity.NewOrder;
import com.example.RunDemo.Entity.Person;
import com.example.RunDemo.Repository.OrderRepo;
import com.example.RunDemo.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderService {
@Autowired
private OrderRepo orderRepo;

@Autowired
private PersonRepository personRepository;

// ----------   alternate of autowired----------
//    public OrderService (OrderRepo orderRepo){
//        this.orderRepo=orderRepo;
//    }

    public NewOrder createOrder(NewOrder order, int personId){

        Person id=personRepository.findById(personId).orElseThrow(()->new RuntimeException("person not found"));
         order.setPerson(id);
        return orderRepo.save(order);
    }



}
