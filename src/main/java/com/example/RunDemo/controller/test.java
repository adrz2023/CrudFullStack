package com.example.RunDemo.controller;
import com.example.RunDemo.Dto.PersonDto;
import com.example.RunDemo.Entity.Person;
import com.example.RunDemo.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")

public class test {
    @Autowired
    private PersonService personService;


   @GetMapping("/test")
    public String getTest() {
        System.out.println( "hello world");
        return "hello world";
    }

   @PostMapping("/saveuser")
    public ResponseEntity<Person> save(@RequestBody Person user){
       return ResponseEntity.ok(personService.saveUser(user));
}

   @GetMapping("/getall")

    public ResponseEntity<List<Person>> all(){

       return ResponseEntity.ok(personService.getAll());
}

  @GetMapping("/{id}")

    public ResponseEntity<Person> getById(@PathVariable  int id) {

    Optional<Person> person = personService.findById(id);
    if (person.isPresent()) {

        return ResponseEntity.ok(person.get());

    } else {
        return ResponseEntity.notFound().build();
    }

}

  @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")

    public ResponseEntity<Person> upadtePerson (@RequestBody Person person ,@PathVariable int id ){
       personService.updatePerson(person,id);
       return ResponseEntity.accepted().build();
    }


    @PostMapping("/login")
    public ResponseEntity<PersonDto> login(@RequestParam String email,@RequestParam String password){
   return ResponseEntity.ok(personService.login(email,password));
    }

}
