package com.example.RunDemo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int personId;
   private String email;
   private String name;
   private String location;
   private String status;
   private String password;
   @JsonIgnore
   @OneToMany(mappedBy="person",cascade = CascadeType.ALL)
   private List<NewOrder> orders;

}
