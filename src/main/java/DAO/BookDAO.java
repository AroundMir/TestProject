package DAO;

import entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM book")
    List<Book> test2();

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE id IN (:ids)")
    List<Book> findByIds(@NotNull @Param("ids") List<Integer> ids);

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE id = (:ids)")
    Book findById(@NotNull @Param("ids") Integer ids);
}
