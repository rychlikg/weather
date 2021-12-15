package pl.rychlik.weather.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rychlik.weather.mapper.CityMapper;
import pl.rychlik.weather.model.dto.CurrentWeatherDto;
import pl.rychlik.weather.model.dto.client.HourlClothing;
import pl.rychlik.weather.repository.CityRepository;
import pl.rychlik.weather.repository.RecordedWeatherRepository;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/current/{cityId}")
@RequiredArgsConstructor
public class CurrentWeatherController {
    private final RecordedWeatherRepository recordedWeatherRepository;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    String rain = "";
    String snow = "";

    /*@GetMapping
    public Date date(){
        return recordedWeatherRepository.getById(Long.parseLong("101")).getDate();
    }*/

   @GetMapping
    public CurrentWeatherDto getCurrent(@PathVariable String cityId){
        Long id = Long.parseLong(cityId);


        CurrentWeatherDto current = new CurrentWeatherDto();
        current.setCity(cityMapper.toDto(cityRepository.getById(id)));


        recordedWeatherRepository.findAll().forEach(recordedWeather -> {
            if(recordedWeather.getCity().getId().equals(id)) {
                current.setCurrentDate(recordedWeather.getDate().toString());
                current.setDescription(recordedWeather.getDescription());
                if(recordedWeather.getMain().equals("Rain")){
                    rain = "true";
                } else rain = "false";
                if(recordedWeather.getMain().equals("Snow")){
                    snow = "true";
                } else snow = "false";
                current.setRain(rain);
                current.setSnow(snow);
                current.setTemp(recordedWeather.getTemp());
                current.setFeels_like(recordedWeather.getFeels_like());
                current.setUvi(recordedWeather.getUvi());
                current.setWind_deg(recordedWeather.getWind_deg());
                current.setWind_gust(recordedWeather.getWind_gust());
                current.setWind_speed(recordedWeather.getWind_speed());

            }
        });
        return current;
    }
}
