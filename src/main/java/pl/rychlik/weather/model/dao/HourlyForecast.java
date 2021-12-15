package pl.rychlik.weather.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class HourlyForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private City city;

    private Date date;
    private double temp;
    private double feels_like;
    private long pressure;
    private long humidity;
    private double uvi;
    private long clouds;
    private long visibility;
    private double wind_speed;
    private double wind_deg;
    private double wind_gust;
    private  String main;
    private String description;
}
