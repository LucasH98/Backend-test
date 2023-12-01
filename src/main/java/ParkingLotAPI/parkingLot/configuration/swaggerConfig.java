package ParkingLotAPI.parkingLot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class swaggerConfig {

	@Bean
	   OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Parkinglot API")
	              .description("Spring boot API sample application")
	              .version("v0.0.1")
	              .license(new License().name("GitHub repository").url("https://github.com/LucasH98/PakinglotSystem-Backend-test")));
	  }
	
}
