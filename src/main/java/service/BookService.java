package service;

import entity.Book;
import entity.Person;
import input.Console;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    public  List<Book> createBook(List<Book> books) {
        System.out.println("Enter name of book");
        String name = Console.read();
        System.out.println("Enter author of book");
        String author = Console.read();
        System.out.println("Enter count of book");
        String count = Console.read();

        books.add(new Book(name, author, books.size()+1, Integer.parseInt(count)));
        return books;
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
