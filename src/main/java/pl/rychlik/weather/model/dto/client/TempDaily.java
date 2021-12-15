package pl.rychlik.weather.model.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempDaily {
    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;
}
