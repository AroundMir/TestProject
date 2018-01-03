import DAO.PersonDAO;
import dto.PersonDTO;
import entity.Person;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

/**
 * Created by Vladimir on 03.01.2018.
 */
public class PersonTest extends BaseIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private PersonDAO personDAO;

	private Map<Integer, Person> resultMap = new HashMap<>();

	@Before
	public void setTestDate() {
		IntStream.rangeClosed(1, 3).forEach(i -> {
			Person person1 = new Person();
			person1.setName("test name");
			person1.setSurname("test surname");
			person1 = personDAO.save(person1);
			resultMap.put(i, person1);
		});
	}

	@Test
	public void testCreate() throws Exception {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setName("test create name");
		personDTO.setSurname("test create surname");

		String body = getObjectMapper().writeValueAsString(personDTO);
		MvcResult mvcResult = mockMvc
				.perform(
						post("/person")
								.contentType(MediaType.APPLICATION_JSON)
								.content(body)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		String contentAsString = mvcResult.getResponse().getContentAsString();
		final Person person = getObjectMapper().readValue(contentAsString, Person.class);

		assertNotNull(person);
		assertNotNull(person.getId());
	}

	@Test
	public void testFindOne() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/person/" + resultMap.get(1).getId()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		String contentAsString = mvcResult.getResponse().getContentAsString();
		Person person = getObjectMapper().readValue(contentAsString, Person.class);
		assertNotNull(person);
		assertNotNull(person.getId());
		assertEquals(person.getId(), resultMap.get(1).getId());
		assertEquals(person.getName(), resultMap.get(1).getName());
	}

	@Test
	public void testUpdate() throws Exception {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setSurname(resultMap.get(2).getSurname());
		personDTO.setName(resultMap.get(2).getName() + " update");
		personDTO.setId(resultMap.get(2).getId());

		MvcResult mvcResult = mockMvc
				.perform(
						put("/person/" + resultMap.get(2).getId())
								.contentType(MediaType.APPLICATION_JSON)
								.content(getObjectMapper().writeValueAsString(personDTO))
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		String contentAsString = mvcResult.getResponse().getContentAsString();
		Person person = getObjectMapper().readValue(contentAsString, Person.class);
		assertNotNull(person);
		assertNotNull(person.getId());
		assertEquals(person.getName(), resultMap.get(2).getName() + " update");
		assertEquals(person.getId(), resultMap.get(2).getId());
	}

	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(delete("/person/" + resultMap.get(3).getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		assertNull(personDAO.findOne(resultMap.get(3).getId()));
	}
}
