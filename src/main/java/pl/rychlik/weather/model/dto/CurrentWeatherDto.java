package pl.rychlik.weather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rychlik.weather.model.dao.City;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentWeatherDto {
    public CityDto city;
    public String currentDate;
    public String rain;
    public String snow;
    public String description;
    public double temp;
    public double feels_like;
    public double uvi;
    public double wind_speed;
    public double  wind_gust;
    public double wind_deg;


}
