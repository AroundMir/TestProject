package controller;

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
	public Book createBook(@RequestBody BookDTO bookDTO) {
		return bookService.save(bookDTO.toEntity());
	}

	@RequestMapping(path = "/{bookId}", method = RequestMethod.PUT)
	public ResponseEntity updateBook(@PathVariable(name = "bookId", required = true) Integer id,
									 @RequestBody BookDTO bookDTO) {
		if (!bookService.checkBookId(id)) {
			return ResponseEntity.badRequest().body("id not fount");
		}
		Book book = bookDTO.toEntity();
		book.setId(id);
		return ResponseEntity.ok(bookService.save(book));
	}

	@RequestMapping(path = "/{bookId}", method = RequestMethod.GET)
	public Book getById(@PathVariable(name = "bookId", required = true) Integer id) {
		return bookService.findById(id);
	}

	@RequestMapping(path = "/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity deleteById(@PathVariable(name = "bookId", required = true) Integer id) {
		try {
			bookService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Throwable ex ) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
}
