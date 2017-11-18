package service;

import DAO.OrderDAO;
import entity.Book;
import entity.Order;
import entity.Person;
import input.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public void orderTest(){

        Order order = orderDAO.findOne(1);
        String a = "1";
    }

    public void buyBook(List<Book> books, List<Person> persons, List<Order> orders,
                 PersonService personService, BookService bookService) {

        System.out.println("Enter id Person who buy the book");
        Integer personId = Integer.parseInt(Console.read());

        System.out.println("Enter id of book to buy");
        Integer bookId = Integer.parseInt(Console.read());

        System.out.println("Enter count of books to buy");
        Integer bookOrderCount = Integer.parseInt(Console.read());




        if (personService.checkPersonId(personId, persons) & bookService.checkId(bookId, books) &
                bookOrderCount <= books.get(bookId).getCount()) {

            //Создаем коллекцию, но пока что с книгами одного id. В интерфейсе
            //потом будет возможность сделать заказ с несколькими.
            List<Book> booksOrder = new ArrayList<>();
            int i = 0;
            while (i <= bookOrderCount) {
                Book orderedBook = books.get(bookId);
                booksOrder.add(orderedBook);
                i++;
            }


            Integer bookCount = books.get(bookId).getCount() - bookOrderCount;
            Book book = books.get(bookId);
            book.setCount(bookCount);

            orders.add(new Order(persons.get(personId), booksOrder));
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
