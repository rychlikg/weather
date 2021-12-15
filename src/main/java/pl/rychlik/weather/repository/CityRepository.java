package pl.rychlik.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rychlik.weather.model.dao.City;

public interface CityRepository extends JpaRepository<City,Long> {
}
