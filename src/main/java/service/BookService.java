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

        List<Review> reviews = new ArrayList<>();
        if(!CollectionUtils.isEmpty(reviewDAO.findAll())){
            reviews = reviewDAO.findAll().stream().
                    filter(s -> s.getBookId()  == book.getId()).
                    collect(Collectors.toList());

            for(Review r : reviews){
               book.addReview(r);
            }
        }
try {
    return bookDAO.save(book);
} catch (Throwable ex){

            ex.getMessage();
}

return null;
    }

    //удаление по ИД
    @Transactional
    public void delete(Integer id) {
        bookDAO.delete(id);
    }

    public Book findById(Integer id){

        Book book = bookDAO.findOne(id);

        List<Review> reviews = new ArrayList<>();
        if(!CollectionUtils.isEmpty(reviewDAO.findAll())) {
            reviews = reviewDAO.findAll().stream().
                    filter(s -> s.getBookId() == book.getId()).
                    collect(Collectors.toList());

            for (Review r : reviews) {
                book.addReview(r);
            }
        }

        try{
            return book;
        } catch (Throwable ex){
            ex.getMessage();
        }

return null;
        }

    public List<Book> showAll() {
        return bookDAO.findAll();
        }

    public boolean checkOnExist(Integer id) {
        return bookDAO.exists(id);
    }

}
