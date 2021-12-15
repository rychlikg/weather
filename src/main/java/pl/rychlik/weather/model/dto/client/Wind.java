package pl.rychlik.weather.model.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wind {
    private double speed;
    private double deg;
    private double gust;
}
