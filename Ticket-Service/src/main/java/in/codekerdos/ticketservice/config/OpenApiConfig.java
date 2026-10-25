package in.codekerdos.ticketservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration 
public class OpenApiConfig { /// This class is for test the APIs using swagger

    @Bean
    public OpenAPI ticketServiceOpenAPI(){
        return new OpenAPI()
               .info(new Info()
                .title("CodeKerdos ticket service")
                .description("Project #1 - AI Support Ticket Router: Ticket CRUD + AI classifier ")
                .version("1.0"));  
    }
    
}

//Swagger url: http://localhost:8080/swagger-ui/index.html
