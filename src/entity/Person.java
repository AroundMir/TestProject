package entity;

public class Person {

    private String name;
    private String Surname;
    private  int id;


    public Person(String name, String surname, int id) {
        this.name = name;
        this.Surname = surname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return Surname;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }
}
