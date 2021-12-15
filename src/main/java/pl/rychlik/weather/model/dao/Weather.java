package pl.rychlik.weather.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String main;
    private String description;
    private double temp;
    private double fillTemp;
    private double tempMin;
    private double tempMax;
    private double pressure;
    private double humidity;
    private double visibility;
    private double windSpeed;
    private double deg;
    private double gust;
    private double clouds;

    @ManyToOne
    private City city;

    @CreatedDate
    private LocalDateTime createdDate;
}
