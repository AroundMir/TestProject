package dto;

import entity.Book;

/**
 * Created by Vladimir on 28.11.2017.
 */
public class BookDTO {

	private String name;

	private String author;

	private int count;

	public BookDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Book toEntity() {
		Book book = new Book();
		book.setAuthor(this.author);
		book.setName(this.name);
		book.setCount(this.count);
		return book;
	}
}
