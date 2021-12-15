package pl.rychlik.weather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rychlik.weather.model.dto.client.DailyForcast;
import pl.rychlik.weather.model.dto.client.FeelsLikeDaily;
import pl.rychlik.weather.model.dto.client.TempDaily;
import pl.rychlik.weather.model.dto.client.WeatherDescription;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyForcastDto {
    List<DailyForcast> daily;
}
