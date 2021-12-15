package pl.rychlik.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rychlik.weather.model.dao.Weather;

public interface WeatherRepository extends JpaRepository<Weather,Long> {
}
