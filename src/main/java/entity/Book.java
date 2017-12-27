package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String author;

    @Column(name = "count")
    private int count;

    public Book() {
    }



    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    public Book(String name, String author, int id, int count) {
        this.name = name;
        this.author = author;
        this.id = id;
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews(){
        return this.reviews;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return  author;
    }

    public int getId() {
        return this.id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return getId()+" "+getName()+" "+getAuthor()+" "+getAuthor();
    }
}
