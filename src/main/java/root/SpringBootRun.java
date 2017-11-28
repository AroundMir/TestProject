package root;

import conf.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ControllerConfig.class, DataConfig.class, ServiceConfig.class, SwaggerConfig.class})
public class SpringBootRun extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(SpringBootRun.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(SpringBootRun.class);
    }
}

