import com.fasterxml.jackson.databind.ObjectMapper;
import conf.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import root.SpringBootRun;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootRun.class},
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Ignore
public class BaseIntegrationTest extends Assert {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
