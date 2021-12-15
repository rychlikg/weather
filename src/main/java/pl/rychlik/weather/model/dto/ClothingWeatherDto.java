package pl.rychlik.weather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rychlik.weather.model.dto.client.HourlClothing;
import pl.rychlik.weather.model.dto.client.Hourly;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClothingWeatherDto {
    public CityDto city;
    public boolean rain;
    public boolean snow;
    public String description;
    public double temp;
    public double feels_like;
    public double uvi;
    public double wind_speed;
    public List<HourlClothing> hourly;
}
