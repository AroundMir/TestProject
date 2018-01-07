package dto;
import entity.Book;
import entity.Review;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ReviewDTO {

    private Integer id;

    private String name;

    private BookDTO book;

    public Review toEntity(){
        Review review = new Review();
        review.setName(this.name);
        review.setBook(this.book != null ? this.book.toEntity() : null);
        return  review;
    }
}
