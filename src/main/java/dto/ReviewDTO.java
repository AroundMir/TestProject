package dto;
import entity.Book;
import entity.Review;

import javax.persistence.*;


public class ReviewDTO {


    private Integer id;

    private String name;

    private Book book;

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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Review toEntity(){
        Review review = new Review();
        review.setName(this.name);
        review.setBook(this.book);
        return  review;
    }

}
