package com.example.RunDemo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String email;
   private String name;
   private String location;
   private String status;
   private String password;
}
