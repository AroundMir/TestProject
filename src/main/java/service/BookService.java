package service;

import DAO.BookDAO;
import DAO.ReviewDAO;
import entity.Book;
import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    @Transactional
    public Book save(Book book) {
        return bookDAO.save(book);
    }

    //удаление по ИД
    @Transactional
    public void delete(Integer id) {
        bookDAO.delete(id);
    }

    public Book find(Integer id){
        return bookDAO.findById(id);
    }

    public List<Book> showAll() {
        return bookDAO.findAll();
        }

    public boolean checkBookId(Integer id) {
        return bookDAO.exists(id);
    }
}
