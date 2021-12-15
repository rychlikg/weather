package pl.rychlik.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.rychlik.weather.repository.CityRepository;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableJpaAuditing
public class WeatherApplication {


	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);

	}

}
