package entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "book_id")
    private List<Book> books;

    public Order(){

    }

    public Order(Person person, List<Book> books){
        this.person = person;
        this.books = books;
    }

    public List<Book> getBook() {
        return this.books;
    }

    public Person getPerson() {

        return this.person;
    }

    public void setBook(List<Book> books) {
        this.books = books;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return getBook().toString() + " " + getPerson().toString();
    }
}
