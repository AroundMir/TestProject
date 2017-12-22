package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String author;

    @Column(name = "count")
    private int count;

    public Book() {
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList;

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

    public void setReviewList(List<Review> reviewList){
        this.reviewList = reviewList;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return  this.author;
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

    public List<Review> getAllReviews(){
        return reviewList;
    }

    @Override
    public String toString() {
        return getId()+" "+getName()+" "+getAuthor()+" "+getAuthor();
    }
}
