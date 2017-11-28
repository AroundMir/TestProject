package service;

import DAO.PersonDAO;
import entity.Person;
import input.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {


    @Autowired
    private PersonDAO personDAO;

    public List<Person> person() {
        List<Person> persons = personDAO.findAll();
        return persons;
    }

    public Person createPerson(String name, String surname, Integer id) {
        return personDAO.save(new Person(name, surname, id));
    }

    public boolean deletePerson(Integer id) {
        if (checkPersonId(id)) {
            personDAO.delete(id);
            return true;
        }
        return false;
    }

   public Person findPerson(Integer id)  {
        if  (checkPersonId(id)){
            Person person = personDAO.findAll().stream().filter(s -> s.getId() == id).collect(Collectors.toList()).get(id);
            return person;
        }
     return null;
    }

    public List<Person> showAllPersons(){
        List<Person> persons = personDAO.findAll();
        return persons;
    }

    public boolean checkPersonId(Integer id) {
        List<Person> persons = personDAO.findAll();
        return persons.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).size() > 0;
    }

}
