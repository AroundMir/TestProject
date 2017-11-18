package service;

import DAO.BookDAO;
import entity.Book;
import entity.Person;
import input.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public void testService(){

        List<Book> list = bookDAO.test2();
        String a = "";
    }

    @Transactional
    public  Book createBook(Book book) {
        return bookDAO.save(book);
    }

    public void deleteBook(List<Book> books) {
        System.out.println("Enter id of book");
        Integer id = Integer.parseInt(Console.read());
        if(checkId(id, books)){
            books = books.stream().filter(s -> s.getId() != id).collect(Collectors.toList());
            System.out.println("Book was delete in base");
        } else {
            System.out.println("Id not found");
        }
    }

    public void findBook(List<Book> books){
        System.out.println("Enter id of book");
        Integer id = Integer.parseInt(Console.read());
        if(checkId(id, books)){
            Book book = books.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).get(id);
            System.out.println(book);
        }
    }

    public void showAll(List<Book> books) {
        books.stream().forEach(b -> System.out.println(b));
        }

    public boolean checkId(int id, List<Book> books) {
        return books.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).size() > 0;
    }
}
