package pl.rychlik.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rychlik.weather.model.dao.HourlyForecast;

public interface HourlyForecastRepository extends JpaRepository<HourlyForecast,Long> {
}
