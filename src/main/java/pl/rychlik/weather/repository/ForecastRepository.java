package pl.rychlik.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rychlik.weather.model.dao.HourlyForecast;

public interface ForecastRepository extends JpaRepository<HourlyForecast,Long> {
}
