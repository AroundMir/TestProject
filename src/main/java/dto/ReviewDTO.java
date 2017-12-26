package dto;
import entity.Book;
import entity.Review;

import javax.persistence.*;


public class ReviewDTO {


    private Integer id;

    private String name;

    private Integer book_id;

    public ReviewDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBook_id() {
        return this.book_id;
    }

    public void setBook_id(Integer id) {
        this.book_id = id;
    }

    public Review toEntity(){
        Review review = new Review();
        review.setName(this.name);
        review.setBook_id(this.id);
        return  review;
    }

}
