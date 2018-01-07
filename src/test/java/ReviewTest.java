import DAO.BookDAO;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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

            reviewMap.put(i, review);
            reviewDAO.save(review);
        });
    }

    @Test
    public void checkDataBase(){
        List<Review> reviewList = reviewDAO.findAll().stream().collect(Collectors.toList());

        reviewList.stream().forEach(s -> System.out.println(s.toString()));
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
                        post("/review")
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

    @Test
    public void testPostDb() throws Exception {

        Review daoTestReview = reviewDAO.findOne(1);
        Review mapTestReview = reviewMap.get(1);
        //Book book = reviewDAO.findById(1).getBook();

        String body = getObjectMapper().writeValueAsString(mapTestReview);

        MvcResult mvcResult = mockMvc
                .perform(
                        post("/review")
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

    @Test
    public void testFindOne() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(
                        get("/review/" + reviewDAO.findOne(1).getId())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        final Review review = getObjectMapper().readValue(contentAsString, Review.class);

        assertNotNull(review);
        assertNotNull(review.getId());
        assertNotNull(review.getBook());

        assertEquals(review.getId(), reviewMap.get(1).getId());
        assertEquals(review.getBook().getId(), reviewMap.get(1).getBook().getId());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setName(reviewMap.get(1).getName());

        reviewDTO.setId(reviewMap.get(1).getId());


        MvcResult mvcResult = mockMvc
                .perform(
                        put("/review/" + reviewDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectMapper().writeValueAsString(reviewDTO))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        final Review review = getObjectMapper().readValue(contentAsString, Review.class);

        assertNotNull(review);
        assertNotNull(review.getId());

        assertEquals(review.getId(), reviewMap.get(1).getId());
    }

    @Test
    public void testDelete() throws Exception {

        Review testReview = reviewMap.get(2);
        mockMvc
                .perform(
                        delete("/review/" + testReview.getId())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertNull(reviewDAO.findOne(testReview.getId()));

    }
}


























