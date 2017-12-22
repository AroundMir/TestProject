package service;

import DAO.PersonDAO;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {


    @Autowired
    private PersonDAO personDAO;

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

    public boolean checkOnExist(Integer id) {
        return personDAO.exists(id);
    }

}
