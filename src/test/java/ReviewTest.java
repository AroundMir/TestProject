import DAO.ReviewDAO;
import com.sun.org.apache.regexp.internal.RE;
import dto.BookDTO;
import dto.ReviewDTO;
import entity.Book;
import entity.Review;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ReviewTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewDAO reviewDAO;

    private Map<Integer, Review> reviewMap = new HashMap<>();


    @Before
    public void setTestDate() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Review review = new Review();
            Book testBook = new Book();
            testBook.setAuthor("Test Author");
            testBook.setCount(2);
            review.setName("Test name");
            review.setBook(testBook);
            reviewDAO.save(review);
        });
    }

    @Test
    public void testCreate() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor("test Author");
        bookDTO.setCount(2);
        bookDTO.setName("test Name");
        reviewDTO.setBook(bookDTO);
        reviewDTO.setName("Test name");

        String body = getObjectMapper().writeValueAsString(reviewDTO);

        MvcResult mvcResult = mockMvc
                .perform(
                        post("review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        final Review review = getObjectMapper().readValue(contentAsString, Review.class);

        assertNotNull(review);
        assertNotNull(review.getId());
        assertNotNull(review.getBook());
    }

}





















