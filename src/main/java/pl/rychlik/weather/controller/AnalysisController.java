package pl.rychlik.weather.controller;

import antlr.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rychlik.weather.mapper.CityMapper;
import pl.rychlik.weather.model.dto.AnalysisDto;
import pl.rychlik.weather.repository.CityRepository;
import pl.rychlik.weather.repository.DailyForecastRepository;
import pl.rychlik.weather.repository.RecordedWeatherRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/analysis/{cityId}")
@RequiredArgsConstructor
public class AnalysisController {
    private final RecordedWeatherRepository recordedWeatherRepository;
    private final DailyForecastRepository dailyForecastRepository;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    Date date = new Date();
   // Long aLong;

    @GetMapping
    public AnalysisDto getAnalysis(@PathVariable String cityId) {
        Long id = Long.parseLong(cityId);
        AnalysisDto analysisDto = new AnalysisDto();
        analysisDto.setCityDto(cityMapper.toDto(cityRepository.getById(id)));


        recordedWeatherRepository.findAll().forEach(recordedWeather -> {
            if (recordedWeather.getCity().getId().equals(id)) {
                analysisDto.setDate(recordedWeather.getDate().toString());
                date = recordedWeather.getDate();
                analysisDto.setCurrentTemp(recordedWeather.getTemp());
                analysisDto.setCurrentFeelsLike(recordedWeather.getFeels_like());
                analysisDto.setCurrentWindSpeed(recordedWeather.getWind_speed());
                analysisDto.setCurrrentPressure(recordedWeather.getPressure());
            }
        });
       dailyForecastRepository.findAll().forEach(dailyForcast -> {
            if (dailyForcast.getCity().getId().equals(id)) {
                if (dailyForcast.getDate().toString().substring(dailyForcast.getDate().toString().indexOf("2"),dailyForcast.getDate().toString().indexOf(" ")).equals(date.toString().substring(date.toString().indexOf("2"),date.toString().indexOf(" ")))) {
                    analysisDto.setPredictedTemp(dailyForcast.getTempDay());
                    analysisDto.setPredictedNightTemp(dailyForcast.getTempNight());
                    analysisDto.setPredictedFeelsLike(dailyForcast.getFeels_likeDay());
                    analysisDto.setPredictedFeelsLikeNight(dailyForcast.getFeels_likeNight());
                    analysisDto.setPredictedWindSpeed(dailyForcast.getWind_speed());
                    analysisDto.setPredictedPressure(dailyForcast.getPressure());
                    //aLong = dailyForcast.getId();
                }
            }
        });


        //System.out.println(aLong);
        return analysisDto;
    }
}
