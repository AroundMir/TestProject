package service;

import DAO.BookDAO;
import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    //удаление по ИД
    public boolean deleteBook(Integer id) {
        if(checkBookId(id)){
            bookDAO.delete(id);
            return true;
        }
        return false;
    }

    public Book findBook(List<Book> books, Integer id){
        if(checkBookId(id)){
            Book book = books.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).get(id);
            return book;
        }
        return null;
    }

    public List<Book> showAll() {
        return bookDAO.findAll();
        }

    public boolean checkBookId(Integer id) {

        if(bookDAO.findOne(id).equals(Optional.empty()))
            return false;
        else return true;

    }
}
