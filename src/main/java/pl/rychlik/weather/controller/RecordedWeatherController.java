package pl.rychlik.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rychlik.weather.client.RecordedClient;
import pl.rychlik.weather.model.dto.WeatherDto;
import pl.rychlik.weather.repository.CityRepository;

@RestController
@RequestMapping("/api/recorded/{cityId}")
@RequiredArgsConstructor
public class RecordedWeatherController {
    private final CityRepository cityRepository;
    private final RecordedClient recordedClient;
    @GetMapping
    public WeatherDto test(@PathVariable String cityId){
        Long id = Long.parseLong(cityId);
        return recordedClient.getRecorded(cityRepository.getById(id).getLat(),cityRepository.getById(id).getLon(),cityRepository.getById(id).getUnits(),cityRepository.getById(id).getLanguage(),"minutely,hourly,daily,alerts","e7a3032d9c5cc3ac7d6dd19e0e175612");
    }
}
