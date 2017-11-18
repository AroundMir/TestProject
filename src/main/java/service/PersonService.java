package service;

import entity.Person;
import input.Console;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    public List<Person> createPerson(List<Person> persons) {
        System.out.println("Enter name of Person");
        String name = Console.read();
        System.out.println("Enter surname of Person");
        String surname = Console.read();

        persons.add(new Person(name, surname, persons.size()+1));
        return persons;
    }

    public void deletePerson(List<Person> persons) {
        System.out.println("Enter name of Person");
        Integer id = Integer.parseInt(Console.read());
        if(checkPersonId(id, persons)){
            persons = persons.stream().filter(s -> s.getId() != id).collect(Collectors.toList());
            System.out.println("Person was delete");
        } else {
            System.out.println("Person is not found");
        }
    }

   public void findPerson(List<Person> persons){

        System.out.println("Enter id of Person");
        Integer id = Integer.parseInt(Console.read());

        if  (checkPersonId(id, persons)){

            Person person = persons.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).get(id);
            System.out.println(person);
        }
    }

    public void showAllPersons(List<Person> persons){
        persons.stream().forEach(s -> System.out.println(persons));
    }


    public boolean checkPersonId(int id, List<Person> persons) {
        return persons.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).size() > 0;
    }

}
