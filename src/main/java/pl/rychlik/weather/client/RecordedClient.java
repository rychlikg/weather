package pl.rychlik.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rychlik.weather.model.WeatherUnit;
import pl.rychlik.weather.model.dto.DailyForcastDto;
import pl.rychlik.weather.model.dto.WeatherDto;

@FeignClient(url = "http://api.openweathermap.org", name = "recorded")
public interface RecordedClient {
    @GetMapping("data/2.5/onecall")
    WeatherDto getRecorded(@RequestParam String lat, @RequestParam String lon, @RequestParam WeatherUnit units, @RequestParam String lang, @RequestParam String exclude, @RequestParam String appid);

    @GetMapping("data/2.5/onecall")
    DailyForcastDto getDaily(@RequestParam String lat, @RequestParam String lon, @RequestParam WeatherUnit units, @RequestParam String lang, @RequestParam String exclude, @RequestParam String appid);

}