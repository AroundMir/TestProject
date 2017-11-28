package controller;

import DAO.BookDAO;
import entity.Book;
import entity.Order;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;
import service.OrderService;
import service.PersonService;

import java.util.List;

@RestController
public class MainController {


    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PersonService personService;

    @RequestMapping(path = "test1", method = RequestMethod.GET)
    public String test1() {
        System.out.println("Tets1");
        bookService.testService();
        return "Test1";
    }

    @RequestMapping(path = "test2", method = RequestMethod.GET)
    public Order test2() {
        System.out.println("Tets2");
        return orderService.orderTest();
    }

    @RequestMapping(path = "bookCreate", method = RequestMethod.GET)
    public Book createBook() {
        Book book = new Book("Author5","name5", 5, 5);
        return bookService.createBook(book);
    }
}
