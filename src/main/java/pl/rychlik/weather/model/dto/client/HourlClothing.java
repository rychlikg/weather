package pl.rychlik.weather.model.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rychlik.weather.model.dto.CityDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HourlClothing {
    public CityDto city;
    public String date;
    public boolean rain;
    public boolean snow;
    public String description;
    public double temp;
    public double feels_like;
    public double uvi;
    public double wind_speed;
}
