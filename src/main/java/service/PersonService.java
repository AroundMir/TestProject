package service;

import DAO.PersonDAO;
import entity.Person;
import input.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public Person save(Person person) {
        return personDAO.save(person);
    }

    @Transactional
    public void delete(Integer id) {
            personDAO.delete(id);
    }

    public Person find(Integer id)  {
        return personDAO.findOne(id);
    }

    public List<Person> showAll(){
        return personDAO.findAll();
    }

    public boolean checkPersonId(Integer id) {
        return personDAO.exists(id);
    }

}
