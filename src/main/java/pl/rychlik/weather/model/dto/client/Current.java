package pl.rychlik.weather.model.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Current {
    @JsonProperty("dt")
    private long currentDate;
    private long sunrise;
    private long sunset;
    private double temp;
    private double feels_like;
    private Long pressure;
    private Long humidity;
    private double uvi;
    private long clouds;
    private long visibility;
    private double wind_speed;
    private double wind_deg;
    private double wind_gust;
    private List<WeatherDescription> weather;


}
