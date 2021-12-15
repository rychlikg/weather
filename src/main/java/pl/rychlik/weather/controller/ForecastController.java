package pl.rychlik.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rychlik.weather.client.ForecastClient;
import pl.rychlik.weather.model.dto.WeatherDto;
import pl.rychlik.weather.repository.CityRepository;
import pl.rychlik.weather.service.CityService;

@RestController
@RequestMapping("/api/forcast/{cityId}")
@RequiredArgsConstructor
public class ForecastController {
    private final ForecastClient forecastClient;
    private final CityRepository cityRepository;
    private final CityService cityService;
    //private final WeatherRepository weatherRepository;


    @GetMapping
    public WeatherDto test(@PathVariable String cityId){
        Long id = Long.parseLong(cityId);
        return forecastClient.getForecast(cityRepository.getById(id).getLat(),cityRepository.getById(id).getLon(),cityRepository.getById(id).getUnits(),cityRepository.getById(id).getLanguage(),"minutely,hourly,daily,alerts","e7a3032d9c5cc3ac7d6dd19e0e175612");
    }

    /*@GetMapping
    public List<Object> testWeather(){

        cityRepository.findAll().forEach(city -> {
            this.object = forecastClient.getForecast(city.getLat(),city.getLon(),city.getUnits(),city.getLanguage(),"minutely,hourly,daily,alerts","e7a3032d9c5cc3ac7d6dd19e0e175612");
             objects.add(object);
        });
        return  objects;

    }*/

    /*@GetMapping
    public List<Object> test(@PathVariable String city1, @PathVariable String city2){
        long id = cityService.getCityIdByCityName(city1);
        object = forecastClient.getForecast(cityRepository.getById(id).getLat(),cityRepository.getById(id).getLon(),cityRepository.getById(id).getUnits(),cityRepository.getById(id).getLanguage(),"minutely,hourly,daily,alerts","e7a3032d9c5cc3ac7d6dd19e0e175612");
        objects.add(object);
        id = cityService.getCityIdByCityName(city2);
        object = forecastClient.getForecast(cityRepository.getById(id).getLat(),cityRepository.getById(id).getLon(),cityRepository.getById(id).getUnits(),cityRepository.getById(id).getLanguage(),"minutely,hourly,daily,alerts","e7a3032d9c5cc3ac7d6dd19e0e175612");
        objects.add(object);
        object = weatherRepository.findById(id);
        objects.add(object);
        return objects;
    }*/

    /*public RecordedTemperatureDto getCurrentData(@PathVariable String cityId){
        Long id = Long.parseLong(cityId);
        forecastClient.getForecast(cityRepository.getById(id).getLat(),cityRepository.getById(id).getLon(),cityRepository.getById(id).getUnits(),cityRepository.getById(id).getLanguage(),"minutely,hourly,daily,alerts","e7a3032d9c5cc3ac7d6dd19e0e175612");
    }*/


    /*public void fetchWeather(){
        cityRepository.findAll().forEach(city -> {
            WeatherClientDto weather = weatherClient.getWeather(city.getName(), city.getLanguage(), "226223250b295eb5bb85ea0854a15999", city.getUnits().getLabel());
            weatherRepository.save(Weather.builder()
                    .city(city)
                    .clouds(weather.getClouds().getAll())
                    .deg(weather.getWind().getDeg())
                    .main(weather.getWeather().get(0).getMain())
                    .description(weather.getWeather().get(0).getDescription())
                    .fillTemp(weather.getMain().getFillTemp())
                    .gust(weather.getWind().getGust())
                    .humidity(weather.getMain().getHumidity())
                    .pressure(weather.getMain().getPressure())
                    .temp(weather.getMain().getTemp())
                    .tempMax(weather.getMain().getTempMax())
                    .tempMin(weather.getMain().getTempMin())
                    .visibility(weather.getVisibility())
                    .windSpeed(weather.getWind().getSpeed())
                    .build());
        });
    }*/




}
