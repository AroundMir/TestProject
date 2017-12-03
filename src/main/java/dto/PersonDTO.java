package dto;
import entity.Person;



public class PersonDTO {

    private String name;
    private String surname;

    private int id;

    public PersonDTO() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person toEntity() {
        Person person = new Person();
        person.setName(this.name);
        person.setSurname(this.surname);
        return person;
    }
}
