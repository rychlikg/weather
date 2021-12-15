package pl.rychlik.weather.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyForcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private City city;

    private Date date;
    private double tempDay;
    private double tempNight;
    private double feels_likeDay;
    private double feels_likeNight;
    private long pressure;
    private long humidity;
    private double uvi;
    private long clouds;
    private double wind_speed;
    private double wind_deg;
    private double wind_gust;
    private  String main;
    private String description;
}
