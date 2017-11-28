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

    @Transactional
    public Book save(Book book) {
        return bookDAO.save(book);
    }

    //удаление по ИД
    @Transactional
    public void delete(Integer id) {
        bookDAO.delete(id);
    }

    public Book findById(Integer id){
        return bookDAO.findOne(id);
    }

    public List<Book> showAll() {
        return bookDAO.findAll();
        }

    public boolean checkBookId(Integer id) {
        return bookDAO.exists(id);
    }
}
