package com.example.RunDemo;

import com.example.RunDemo.Entity.Person;
import com.example.RunDemo.Repository.PersonRepository;
import com.example.RunDemo.Service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Save valid person successfully")
    void testSaveUser_Success() {
        // Arrange
        Person personToSave = new Person("John Doe", "New York", "Active", "securePassword");
        Person savedPerson = new Person();
        savedPerson.setId(0);
        savedPerson.setName("John Doe");
        savedPerson.setLocation("New York");
        savedPerson.setStatus("Active");
        savedPerson.setPassword("securePassword");

        when(personRepository.save(any(Person.class))).thenReturn(savedPerson);


        // Act
        Person result = personService.saveUser(personToSave);

        // Assert
        assertNotNull(result.getId(), "The saved person ID should not be null");
        assertEquals(savedPerson.getName(), result.getName(), "The name should match the saved person");
        assertEquals(savedPerson.getLocation(), result.getLocation(), "The location should match the saved person");
        assertEquals(savedPerson.getStatus(), result.getStatus(), "The status should match the saved person");
    }

    @Test
    @DisplayName("Throw exception when saving null person")
    void testSaveUser_NullPerson() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> personService.saveUser(null),
                "Saving a null person should throw an IllegalArgumentException");

        assertEquals("Person cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception when saving person with invalid email")
    void testSaveUser_InvalidData() {
        // Arrange
        Person invalidPerson = new Person("John Doe", "New York", "Active", "short");

        when(personRepository.save(any(Person.class)))
                .thenThrow(new IllegalArgumentException("Invalid password"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> personService.saveUser(invalidPerson),
                "Saving a person with invalid data should throw an IllegalArgumentException");

        assertEquals("Invalid password", exception.getMessage());
    }

    @Test
    @DisplayName("Handle database exception while saving person")
    void testSaveUser_RepositoryThrowsException() {
        // Arrange
        Person person = new Person("Jane Doe", "London", "Inactive", "anotherPassword");

        when(personRepository.save(any(Person.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> personService.saveUser(person),
                "Database error should propagate as a RuntimeException");

        assertEquals("Database error", exception.getMessage());
    }
}
