package pl.rychlik.weather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisDto {
    CityDto cityDto;
    private String  date;
    private double currentTemp;
    private double currentFeelsLike;
    private double currentWindSpeed;
    private Long currrentPressure;

    private double predictedTemp;
    private double predictedNightTemp;
    private double predictedFeelsLike;
    private double predictedFeelsLikeNight;
    private double predictedWindSpeed;
    private Long predictedPressure;

}
