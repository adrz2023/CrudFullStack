package com.example.RunDemo.Service;

import com.example.RunDemo.Dto.PersonDto;
import com.example.RunDemo.Entity.NewOrder;
import com.example.RunDemo.Entity.Person;
import com.example.RunDemo.Exception.PersonNotFoundException;
import com.example.RunDemo.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired

    private PersonRepository personRepository;

    public Person saveUser(Person person) throws IllegalArgumentException {
        if(person == null) throw  new IllegalArgumentException("Can't accept null");
        return personRepository.save(person);
    }


    public List<Person> getAll() {

        return personRepository.findAll();
    }


    public Optional<Person> findById(int id) {
        return Optional.ofNullable(personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person with ID " + id + " not found")));
    }




    public PersonDto login(String email, String password) {
        Optional<Person> person = personRepository.findByEmailAndPassword(email, password);
        Person foundPerson = person.orElseThrow(() -> new PersonNotFoundException("Invalid email or password"));

        return new PersonDto(
                foundPerson.getPersonId(),
                foundPerson.getEmail(),
                foundPerson.getName(),
                foundPerson.getLocation(),
                foundPerson.getStatus()
        );
    }




    public Person updatePerson(Person person, int id) {

        Person existingPerson = personRepository.findById(id)
                .orElseThrow(()->new PersonNotFoundException("person with this id is not found"));


            existingPerson.setName(person.getName());
            existingPerson.setLocation(person.getLocation());
            existingPerson.setStatus(person.getStatus());
            existingPerson.setPassword(person.getPassword());
            existingPerson.setEmail(person.getEmail());


            return personRepository.save(existingPerson);
        }

  public List<NewOrder> getOrdersByPersonId(int personId) {
       Person person=personRepository.findById(personId).orElseThrow(()->new RuntimeException());

       return person.getOrders();
  }

    public void delete(int id) {
       personRepository.deleteById(id);
    }




}