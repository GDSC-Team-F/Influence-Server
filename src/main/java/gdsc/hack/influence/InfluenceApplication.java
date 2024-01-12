package gdsc.hack.influence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InfluenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfluenceApplication.class, args);
	}

}
