import DAO.OrderDAO;
import DAO.PersonDAO;
import dto.OrderDTO;
import dto.PersonDTO;
import entity.Book;
import entity.Order;
import entity.Person;
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

public class OrderTest extends BaseIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    PersonDAO personDAO;

    HashMap<Integer, Order> orderMap = new HashMap<>();
    HashMap<Integer, Person> personMap = new HashMap<>();

    @Before
    public void setTestDate(){
        IntStream.rangeClosed(1,3).forEach(i -> {

            Person person = new Person();
            person.setName("test name");
            person.setSurname("test surname");

            personMap.put(1, person);
            person = personDAO.save(person);

            Order testOrder = new Order();
            testOrder.setPerson(person);

            orderMap.put(i, testOrder);
            orderDAO.save(testOrder);
        });
    }

    @Test
    public void checkDataBase(){
        List<Order> orders = orderDAO.findAll().stream().collect(Collectors.toList());
        orders.stream().forEach(s -> System.out.println(s.getPerson()));
    }

    @Test
    public void testCreate() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPerson(personMap.get(1));

        String body = getObjectMapper().writeValueAsString(orderDTO);

        MvcResult mvcResult = mockMvc
                .perform(
                        post("/order")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        final Order order = getObjectMapper().readValue(contentAsString, Order.class);

        assertNotNull(order);
        assertNotNull(order.getId());
        assertNotNull(order.getPerson());
        System.out.println(order.getPerson().getId());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPerson(personMap.get(1));
        orderDTO.setId(orderMap.get(2).getId());

        MvcResult mvcResult = mockMvc
                .perform(
                        put("/order/" + orderDTO.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getObjectMapper().writeValueAsString(orderDTO))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        final Order order = getObjectMapper().readValue(contentAsString, Order.class);

        assertNotNull(order);
        assertNotNull(order.getId());

        assertEquals(order.getId(), orderMap.get(2).getId());
    }

    @Test
    public void testDelete() throws Exception {

        Order order = orderMap.get(1);

        mockMvc
                .perform(
                        delete("/order/" + order.getId())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertNull(orderDAO.findOne(order.getId()));
    }

    @Test
    @Transactional
    public void testFindOne() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(
                        get("/order/" + orderDAO.findOne(1).getId())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Order order = getObjectMapper().readValue(contentAsString, Order.class);

        assertNotNull(order);
        assertNotNull(order.getId());

        assertEquals(order.getId(), orderMap.get(1).getId());

    }
}
