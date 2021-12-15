package pl.rychlik.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rychlik.weather.model.dao.City;
import pl.rychlik.weather.repository.CityRepository;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    long id = 0;

    public City save(City city){
        return cityRepository.save(city);
    }
    public long getCityIdByCityName(String cityName) {

        cityRepository.findAll().forEach(city -> {
            if(city.getName().equals(cityName)) {
                this.id = city.getId();
            }
        });
        return id;
    }
}
