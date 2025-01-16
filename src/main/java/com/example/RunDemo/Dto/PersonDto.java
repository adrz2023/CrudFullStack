package com.example.RunDemo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    private  int id;
    private String email;
    private String name;
    private String location;
    private String status;

    public PersonDto(int id, String email, String name, String location, String status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.location = location;
        this.status = status;
    }
}
