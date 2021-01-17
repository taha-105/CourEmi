package ma.ac.emi.coursemi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CategoriesServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoriesServicesApplication.class, args);
	}

}
