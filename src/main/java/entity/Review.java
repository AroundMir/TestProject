package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Review implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	private Book book;

	public Review() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Book getBook() {
		return this.book;
	}

	public Integer getBookId() {
	    return this.book.getId();
    }

	public void setBook(Book book) {
		this.book = book;
	}
}
