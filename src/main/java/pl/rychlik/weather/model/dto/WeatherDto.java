package pl.rychlik.weather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rychlik.weather.model.dto.client.Current;
import pl.rychlik.weather.model.dto.client.Hourly;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {
    private Current current;
    private List<Hourly> hourly;
}
