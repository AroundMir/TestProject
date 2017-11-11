package service;

import entity.Book;
import entity.Order;
import entity.Person;
import input.Console;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OrderService {

    public void buyBook(List<Book> books, List<Person> persons, List<Order> orders,
                 PersonService personService, BookService bookService) {

        System.out.println("Enter id Person who buy the book");
        Integer personId = Integer.parseInt(Console.read());

        System.out.println("Enter id of book to buy");
        Integer bookId = Integer.parseInt(Console.read());

        if (personService.checkPersonId(personId, persons) & bookService.checkId(bookId, books)) {
            Integer bookCount = books.get(bookId).getCount() - 1;
            Book book = books.get(bookId);
            book.setCount(bookCount);
            orders.add(new Order(persons.get(personId), books.get(bookId)));
        }
        System.out.println("The book was bought");
    }

    public void showPersonOrders(List<Book> books, List<Person> persons, List<Order> orders,
                                 PersonService personService){

        System.out.println("Enter Person id");
        Integer personId = Integer.parseInt(Console.read());

        if (personService.checkPersonId(personId, persons)) {
            orders.stream().filter(s -> s.getPerson().getId() == personId).collect(Collectors.toList()).forEach(s -> System.out.println(s));
        }
    }

     public void showAllOrders(List<Order> orders){
        orders.stream().forEach(s -> System.out.println(orders));
    }
}
