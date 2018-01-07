package controller;

import com.sun.org.apache.regexp.internal.RE;
import dto.BookDTO;
import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.*;

@RestController
@RequestMapping(path = "book")
public class BookController {

	@Autowired
	private BookService bookService;


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
		return ResponseEntity.ok(bookService.save(bookDTO.toEntity()));
	}

	@RequestMapping(path = "/{bookId}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@PathVariable(name = "bookId", required = true) Integer id,
									 @RequestBody BookDTO bookDTO) {
		if (!bookService.checkBookId(id)) {
			return ResponseEntity.badRequest().build();
		}
		Book book = bookDTO.toEntity();
		book.setId(id);
		return ResponseEntity.ok(bookService.save(book));
	}

	@RequestMapping(path = "/{bookId}", method = RequestMethod.GET)
	public ResponseEntity<Book> getById(@PathVariable(name = "bookId", required = true) Integer id) {
		return ResponseEntity.ok(bookService.find(id));
	}

	@RequestMapping(path = "/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteById(@PathVariable(name = "bookId", required = true) Integer id) {
		try {
			bookService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Throwable ex ) {
			return ResponseEntity.badRequest().build();
		}
	}
}
