package com.example.RunDemo.Repository;

import com.example.RunDemo.Entity.NewOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<NewOrder,Integer> {
}
