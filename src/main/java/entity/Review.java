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

	@ManyToOne(optional = true, targetEntity = Book.class)
	@JoinColumn(name = "book_id")
	private Integer book_id;

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


	public Integer getBook_id() {
	    return this.book_id;
    }

	public void setBook_id(Integer id) {
		this.book_id = id;
	}
}
