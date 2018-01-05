package dto;

import entity.Book;
import entity.Review;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 28.11.2017.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class BookDTO {

	private String name;

	private String author;

	private int count;

	private List<Review> reviews;

	public Book toEntity() {
		Book book = new Book();
		book.setAuthor(this.author);
		book.setName(this.name);
		book.setCount(this.count);
		book.setReviews(this.reviews);
		return book;
	}
}
