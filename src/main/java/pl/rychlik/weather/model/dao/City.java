package pl.rychlik.weather.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rychlik.weather.model.WeatherUnit;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = @Index(name = "idx_name_language_units", columnList = "name,language,units",unique = true))
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String language;
    private String lat;
    private String lon;
    @Enumerated(EnumType.STRING)
    private WeatherUnit units;

}
