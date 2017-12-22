package DAO;

import entity.Book;
import entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ReviewDAO extends JpaRepository<Review, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM review WHERE id IN (:ids)")
    List<Review> findByIds(@NotNull @Param("ids") List<Integer> ids);
}
