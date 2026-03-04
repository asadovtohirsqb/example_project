package uz.sqb.example_project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Example Project Swagger UI",
                description = "There are all API which we can use them for testing.",
                version = "v1",
                contact = @Contact(
                        name = "Tokhir Asadov",
                        email = "tasadov@sqb.uz"
                )
        )
)
@SpringBootApplication
public class ExampleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleProjectApplication.class, args);
    }

}
