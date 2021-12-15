package pl.rychlik.weather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rychlik.weather.model.dto.client.Clouds;
import pl.rychlik.weather.model.dto.client.Temperature;
import pl.rychlik.weather.model.dto.client.WeatherDescription;
import pl.rychlik.weather.model.dto.client.Wind;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherClientDto {
    private List<WeatherDescription> weather;
    private Temperature main;
    private double visibility;
    private Wind wind;
    private Clouds clouds;

}
