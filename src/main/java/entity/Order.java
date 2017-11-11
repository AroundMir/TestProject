package entity;


import java.time.LocalDate;

public class Order {

    private Person person;
    private Book book;
    private LocalDate date;

    public Order(Person person, Book book){
        this.person = person;
        this.book = book;
    }

    public Book getBook() {
        return this.book;
    }

    public Person getPerson() {

        return this.person;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return getBook().toString() + " " + getPerson().toString();
    }
}
