package pl.rychlik.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rychlik.weather.model.dao.DailyForcast;

public interface DailyForecastRepository extends JpaRepository<DailyForcast,Long> {
}
