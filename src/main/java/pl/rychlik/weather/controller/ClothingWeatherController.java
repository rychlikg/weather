package pl.rychlik.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rychlik.weather.mapper.CityMapper;
import pl.rychlik.weather.model.dto.ClothingWeatherDto;
import pl.rychlik.weather.model.dto.client.HourlClothing;
import pl.rychlik.weather.repository.CityRepository;
import pl.rychlik.weather.repository.HourlyForecastRepository;
import pl.rychlik.weather.repository.RecordedWeatherRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/clothing/{cityId}")
@RequiredArgsConstructor
public class ClothingWeatherController {
    private final RecordedWeatherRepository recordedWeatherRepository;
    private final HourlyForecastRepository hourlyForecastRepository;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    boolean rain;
    boolean snow;
    Date date = new Date();

    @GetMapping
    public ClothingWeatherDto grtClothing(@PathVariable String cityId){
        Long id = Long.parseLong(cityId);

        ClothingWeatherDto clothig = new ClothingWeatherDto();
        List<HourlClothing> hourlClothingList = new ArrayList<>();

        clothig.setCity(cityMapper.toDto(cityRepository.getById(id)));



        recordedWeatherRepository.findAll().forEach(recordedWeather -> {
            if(recordedWeather.getCity().getId().equals(id)) {
                date = recordedWeather.getDate();
                clothig.setDescription(recordedWeather.getDescription());
                if(recordedWeather.getMain().equals("Rain")){
                    rain = true;
                } else rain = false;
                if(recordedWeather.getMain().equals("Snow")){
                    snow = true;
                } else snow = false;
                clothig.setRain(rain);
                clothig.setSnow(snow);
                clothig.setTemp(recordedWeather.getTemp());
                clothig.setFeels_like(recordedWeather.getFeels_like());
                clothig.setUvi(recordedWeather.getUvi());
                clothig.setWind_speed(recordedWeather.getWind_speed());

            }
        });

        hourlyForecastRepository.findAll().forEach(hourlyForecast -> {
            if(hourlyForecast.getCity().getId().equals(id)){

                if(hourlyForecast.getDate().after(date)&&hourlyForecast.getDate().before(new Date(date.getTime()+(1000*60*60* 24)))){
                    HourlClothing hourlClothing = new HourlClothing();
                    hourlClothing.setCity(cityMapper.toDto(cityRepository.getById(id)));
                    hourlClothing.setDate(hourlyForecast.getDate().toString());
                    hourlClothing.setDescription(hourlyForecast.getDescription());
                    hourlClothing.setFeels_like(hourlyForecast.getFeels_like());
                    if(hourlyForecast.getMain().equals("Rain")){
                        rain = true;
                    } else rain = false;
                    if(hourlyForecast.getMain().equals("Snow")){
                        snow = true;
                    } else snow = false;
                    hourlClothing.setRain(rain);
                    hourlClothing.setSnow(snow);
                    hourlClothing.setTemp(hourlyForecast.getTemp());
                    hourlClothing.setUvi(hourlyForecast.getUvi());
                    hourlClothing.setWind_speed(hourlyForecast.getWind_speed());
                    //System.out.println(hourlClothing.toString());
                    hourlClothingList.add(hourlClothing);

                }
            }
        });
        clothig.hourly = hourlClothingList;
        return clothig;
    }
}
