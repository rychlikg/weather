package pl.rychlik.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rychlik.weather.model.dto.WeatherClientDto;

@FeignClient(url = "http://api.openweathermap.org", name = "weather")
public interface WeatherClient {

    @GetMapping("/data/2.5/weather")
    WeatherClientDto getWeather(@RequestParam String q, @RequestParam String lang, @RequestParam String appid, @RequestParam String units);
}
