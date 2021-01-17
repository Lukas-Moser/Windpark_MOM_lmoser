package windpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootStandaloneActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStandaloneActivemqApplication.class, args);
	}

}
