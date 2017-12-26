package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String author;

    private int count;


    private List<Integer> reviewsID;

    public Book() {
    }

    public Book(String name, String author, int id, int count) {
        this.name = name;
        this.author = author;
        this.id = id;
        this.count = count;
    }

    public void addReviewsID(List<Integer> reviewsID) {
        for(Integer num : reviewsID){
            this.reviewsID.add(num);
        }
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

   /* public Review getReviews(){
        return this.review;
    }*/

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

    public List<Integer> getReviewsID() {
        return reviewsID;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return getId()+" "+getName()+" "+getAuthor()+" "+getAuthor();
    }
}
