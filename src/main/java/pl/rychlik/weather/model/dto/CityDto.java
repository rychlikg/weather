package pl.rychlik.weather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rychlik.weather.model.WeatherUnit;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDto {
    private Long id;
    private String name;
    private String language;
    private String units;
    private String lat;
    private String lon;

}
