package controller;


import dto.BookDTO;
import dto.PersonDTO;
import entity.Book;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import java.io.IOException;

@RestController
@RequestMapping(path = "person")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(method = RequestMethod.POST)
    public Person createBook(@RequestBody PersonDTO personDTO) {
        return personService.save(personDTO.toEntity());
    }

    @RequestMapping(path = "/{personId}", method = RequestMethod.PUT)
    public ResponseEntity updateBook(@PathVariable(name = "personId", required = true) Integer id,
                                     @RequestBody PersonDTO personDTO) {
        if (!personService.checkOnExist(id)) {
            return ResponseEntity.badRequest().body("id not fount");
        }
        Person person = personDTO.toEntity();
        person.setId(id);
        return ResponseEntity.ok(personService.save(person));
    }

    @RequestMapping(path = "/{personId}", method = RequestMethod.GET)
    public Person getById(@PathVariable(name = "personId", required = true) Integer id) {
        return personService.find(id);
    }

    @RequestMapping(path = "/{personId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable(name = "personId", required = true) Integer id){
        try{
            personService.delete(id);
            return ResponseEntity.ok().build();

        } catch (Throwable ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
