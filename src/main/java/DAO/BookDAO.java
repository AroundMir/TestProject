package DAO;

import entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM book")
    List<Book> test2();
}
