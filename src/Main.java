import entity.Book;
import entity.Order;
import entity.Person;
import input.Console;
import service.BookService;
import service.OrderService;
import service.PersonService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        BookService bookService = new BookService();
        PersonService personService = new PersonService();
        OrderService orderService = new OrderService();

        while (true) {
            System.out.println("------------------");
            System.out.println("Input your command");
            System.out.println("------------------");
            String input = Console.read();
            String str = input.toLowerCase();
            switch (str) {
                case "show books":
                    bookService.showAll(books);
                    break;
                case "create book":
                    str.equals("create book");
                    bookService.createBook(books);
                    break;
                case "find book":
                    str.equals("find book");
                    bookService.findBook(books);
                    break;
                case "delete book":
                    str.equals("delete book");
                    bookService.deleteBook(books);
                    break;
                case "show persons":
                    str.equals("show persons");
                    personService.showAllPersons(persons);
                    break;
                case "create person":
                    str.equals("create person");
                    personService.createPerson(persons);
                    break;
                case "find person":
                    str.equals("find person");
                    personService.findPerson(persons);
                    break;
                case "delete person":
                    str.equals("delete person");
                    break;
                case "buy book":
                    str.equals("buy book");
                    orderService.buyBook(books, persons, orders, personService, bookService);
                    break;
                case "show orders":
                    str.equals("show orders");
                    orderService.showAllOrders(orders);
                    break;
                case "show person orders":
                    str.equals("buy book");
                    orderService.buyBook(books, persons, orders, personService, bookService);
                    break;
            }
        }
    }
}

