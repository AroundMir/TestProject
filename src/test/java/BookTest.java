import DAO.BookDAO;
import DAO.ReviewDAO;
import dto.BookDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class BookTest extends BaseIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookDAO bookDAO;

    @Autowired
    ReviewDAO reviewDAO;

    HashMap<Integer, Book> bookMap = new HashMap<>();

    @Before
    public void setTestDate(){
        IntStream.rangeClosed(1,3).forEach(i -> {

            Book testBook = new Book();
            testBook.setName("book name");
            testBook.setCount(3);
            testBook.setAuthor("book Author");
            bookMap.put(i, testBook);
            bookDAO.save(testBook);

        });
    }

    @Test
    public void checkDataBase(){

        List<Book> books = bookDAO.findAll().stream().collect(Collectors.toList());
        books.stream().forEach(s -> System.out.println(s.getName()));
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("test create name");
        bookDTO.setCount(2);
        bookDTO.setAuthor("test Author");

        String body = getObjectMapper().writeValueAsString(bookDTO);

        MvcResult mvcResult = mockMvc
                .perform(
                        post("/book")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        String contentAsString = mvcResult.getResponse().getContentAsString();
        final Book book = getObjectMapper().readValue(contentAsString, Book.class);

        assertNotNull(book);
        assertNotNull(book.getId());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {

        BookDTO bookDTO = new BookDTO();

        bookDTO.setAuthor(bookMap.get(1).getAuthor());
        bookDTO.setCount(bookMap.get(1).getCount());
        bookDTO.setName(bookMap.get(1).getName());
        bookDTO.setId(bookMap.get(1).getId());



        MvcResult mvcResult = mockMvc
                .perform(
                        put("/book/" + bookDTO.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getObjectMapper().writeValueAsString(bookDTO))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        final Book book = getObjectMapper().readValue(contentAsString, Book.class);

        assertNotNull(book);
        assertNotNull(book.getId());

        assertEquals(book.getId(), bookMap.get(1).getId());
    }

    @Test
    public void testDelete() throws Exception {

        Book book = bookMap.get(1);

        mockMvc
                .perform(
                        delete("/book/" + book.getId())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertNull(reviewDAO.findOne(book.getId()));

    }

    @Test
    public void testFindOne() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(
                        get("/book/" + bookDAO.findOne(1).getId())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Book book = getObjectMapper().readValue(contentAsString, Book.class);

        assertNotNull(book);
        assertNotNull(book.getId());

        assertEquals(book.getId(), bookMap.get(1).getId());
        assertEquals(book.getAuthor(), bookMap.get(1).getAuthor());

        System.out.println(book.getReviews());

    }
}
