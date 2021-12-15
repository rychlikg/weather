package pl.rychlik.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rychlik.weather.model.dao.RecordedWeather;

public interface RecordedWeatherRepository extends JpaRepository<RecordedWeather,Long> {
}
