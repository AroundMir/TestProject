package entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "\"order\"")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, targetEntity = Book.class)
    @JoinTable(name = "order_book",
            joinColumns = {
                    @JoinColumn(name = "order_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id", nullable = false)})
    private List<Book> books;

    public Order(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Person getPerson() {

        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
