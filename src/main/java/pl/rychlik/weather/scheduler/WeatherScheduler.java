package pl.rychlik.weather.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.rychlik.weather.client.RecordedClient;
import pl.rychlik.weather.client.WeatherClient;
import pl.rychlik.weather.model.WeatherUnit;
import pl.rychlik.weather.model.dao.City;
import pl.rychlik.weather.model.dao.DailyForcast;
import pl.rychlik.weather.model.dao.HourlyForecast;
import pl.rychlik.weather.model.dao.RecordedWeather;
import pl.rychlik.weather.model.dto.DailyForcastDto;
import pl.rychlik.weather.model.dto.WeatherDto;
import pl.rychlik.weather.repository.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class WeatherScheduler {
    private final WeatherClient weatherClient;
    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;
    private final RecordedWeatherRepository recordedWeatherRepository;
    private final RecordedClient recordedClient;
    private final HourlyForecastRepository hourlyForecastRepository;
    private final DailyForecastRepository dailyForecastRepository;
    boolean isEmpty;


    public void addCitys(){
        City city = new City(Long.parseLong("1"),"Poznań","pl","52.4069","16.9299", WeatherUnit.METRIC);
        cityRepository.save(city);
        city = new City(Long.parseLong("2"),"Wrocław","pl","51.1","16.9299", WeatherUnit.METRIC);
        cityRepository.save(city);
        city = new City(Long.parseLong("3"),"Gdańsk","pl","54.3521","18.6464", WeatherUnit.METRIC);
        cityRepository.save(city);
        city = new City(Long.parseLong("4"),"Warszawa","pl","52.2298","21.0118", WeatherUnit.METRIC);
        cityRepository.save(city);
        city = new City(Long.parseLong("5"),"Kraków","pl","50.0833","19.9167", WeatherUnit.METRIC);
        cityRepository.save(city);
        city = new City(Long.parseLong("6"),"Łódź","pl","51.5","19,5", WeatherUnit.METRIC);
        cityRepository.save(city);
        city = new City(Long.parseLong("7"),"Bydgoszcz","pl","53.1235","18.0076", WeatherUnit.METRIC);
        cityRepository.save(city);
        city = new City(Long.parseLong("8"),"Toruń","pl","53.0137","18.5981", WeatherUnit.METRIC);
        cityRepository.save(city);
        city = new City(Long.parseLong("9"),"Katowice","pl","50.2584","19.0275", WeatherUnit.METRIC);
        cityRepository.save(city);
        city = new City(Long.parseLong("10"),"Szczecin","pl","53.4289","19.0275", WeatherUnit.METRIC);
        cityRepository.save(city);
    }


    @Scheduled(cron = "0 0 * * * *")
   //@Scheduled(fixedDelay = 1000*60*60)
    public void saveCurrentWeather(){
        //System.out.println("Odpala się");
        if(cityRepository.findAll().isEmpty()) addCitys();
        hourlyForecastRepository.deleteAll();

        cityRepository.findAll().forEach(city -> {
            WeatherDto recordedWeatherDto = recordedClient.getRecorded(city.getLat(), city.getLon(),city.getUnits(),city.getLanguage(),"minutely,daily,alerts","e7a3032d9c5cc3ac7d6dd19e0e175612" );
            recordedWeatherRepository.save(RecordedWeather.builder()
                    .city(city)
                    .clouds(recordedWeatherDto.getCurrent().getClouds())
                    .date( new java.util.Date(recordedWeatherDto.getCurrent().getCurrentDate()*1000))
                    .description(recordedWeatherDto.getCurrent().getWeather().get(0).getDescription())
                    .feels_like(recordedWeatherDto.getCurrent().getFeels_like())
                    .humidity(recordedWeatherDto.getCurrent().getHumidity())
                    .main(recordedWeatherDto.getCurrent().getWeather().get(0).getMain())
                    .pressure(recordedWeatherDto.getCurrent().getPressure())
                    .sunrise(new java.util.Date(recordedWeatherDto.getCurrent().getSunrise()*1000))
                    .sunset(new java.util.Date(recordedWeatherDto.getCurrent().getSunset()*1000))
                    .temp(recordedWeatherDto.getCurrent().getTemp())
                    .uvi(recordedWeatherDto.getCurrent().getUvi())
                    .visibility(recordedWeatherDto.getCurrent().getVisibility())
                    .wind_deg(recordedWeatherDto.getCurrent().getWind_deg())
                    .wind_gust(recordedWeatherDto.getCurrent().getWind_gust())
                    .wind_speed(recordedWeatherDto.getCurrent().getWind_speed())
                    .build());

            recordedWeatherDto.getHourly().forEach(hourly -> {
                hourlyForecastRepository.save(HourlyForecast.builder()
                        .city(city)
                        .clouds(hourly.getClouds())
                        .date(new java.util.Date((hourly.getDate()*1000)))
                        .description(hourly.getWeather().get(0).getDescription())
                        .feels_like(hourly.getFeels_like())
                        .humidity(hourly.getHumidity())
                        .main(hourly.getWeather().get(0).getMain())
                        .pressure(hourly.getPressure())
                        .temp(hourly.getTemp())
                        .uvi(hourly.getUvi())
                        .visibility(hourly.getVisibility())
                        .wind_deg(hourly.getWind_deg())
                        .wind_gust(hourly.getWind_gust())
                        .wind_speed(hourly.getWind_speed())
                        .build());

            });
        });
    }
    //@Scheduled(cron = "0 15 8 * * *")
    @Scheduled(fixedDelay = 1000*60*60*24)
    public void saveDailyForecast(){
       if(dailyForecastRepository.findAll().isEmpty()) {
           isEmpty = true;
           System.out.println("Is Empty");
       } else isEmpty = false;
        dailyForecastRepository.findAll().forEach(dailyForcast -> {
            if(dailyForcast.getDate().after(new Date(new Date().getTime()+(1000*60*60* 24)))){
                dailyForecastRepository.delete(dailyForcast);
            }
        });
        cityRepository.findAll().forEach(city -> {
            DailyForcastDto dailyForcastDto = recordedClient.getDaily(city.getLat(), city.getLon(),city.getUnits(),city.getLanguage(),"current,minutely,hourly,alerts","e7a3032d9c5cc3ac7d6dd19e0e175612" );
            dailyForcastDto.getDaily().forEach(dailyForcast -> {
                if(isEmpty || new java.util.Date(dailyForcast.getDate()*1000).after(new Date(new Date().getTime()+(1000*60*60* 24)))) {
                    //System.out.println("o tak");
                    dailyForecastRepository.save(DailyForcast.builder()
                            .city(city)
                            .date( new java.util.Date(dailyForcast.getDate()*1000))
                            .uvi(dailyForcast.getUvi())
                            .tempDay(dailyForcast.getTemp().getDay())
                            .tempNight(dailyForcast.getTemp().getNight())
                            .feels_likeDay(dailyForcast.getFeels_like().getDay())
                            .feels_likeNight(dailyForcast.getFeels_like().getNight())
                            .pressure(dailyForcast.getPressure())
                            .humidity(dailyForcast.getHumidity())
                            .wind_deg(dailyForcast.getWind_deg())
                            .wind_gust(dailyForcast.getWind_gust())
                            .wind_speed(dailyForcast.getWind_speed())
                            .main(dailyForcast.getWeather().get(0).getMain())
                            .description(dailyForcast.getWeather().get(0).getDescription())
                            .clouds(dailyForcast.getClouds())
                            .build());
                }

            });


        });
    }


    /*@Scheduled(fixedDelay = 1000*60*60)
    public void saveHourlyForcast(){

        cityRepository.findAll().forEach(city -> {
            HourlyForecastDto hourlyForecastDto  = recordedClient.getHourlyForcast(city.getLat(), city.getLon(),city.getUnits(),city.getLanguage(),"current,minutely,daily,alerts","e7a3032d9c5cc3ac7d6dd19e0e175612" );
            hourlyForecastDto.getHourly().forEach(hourly -> {
                hourlyForecastRepository.save(HourlyForecast.builder()
                        .city(city)
                        .clouds(hourly.getClouds())
                        .date(new java.util.Date((hourly.getDate()*1000)))
                        .description(hourly.getWeather().get(0).getDescription())
                        .feels_like(hourly.getFeels_like())
                        .humidity(hourly.getHumidity())
                        .main(hourly.getWeather().get(0).getMain())
                        .pressure(hourly.getPressure())
                        .temp(hourly.getTemp())
                        .uvi(hourly.getUvi())
                        .visibility(hourly.getVisibility())
                        .wind_deg(hourly.getWind_deg())
                        .wind_gust(hourly.getWind_gust())
                        .wind_speed(hourly.getWind_speed())
                        .build());

            });

        });


    }*/

   /* @Scheduled(fixedDelay = 1000*60)
    public void fetchWeather(){
        cityRepository.findAll().forEach(city -> {
            WeatherClientDto weather = weatherClient.getWeather(city.getName(), city.getLanguage(), "226223250b295eb5bb85ea0854a15999", city.getUnits().getLabel());
            weatherRepository.save(Weather.builder()
                    .city(city)
                    .clouds(weather.getClouds().getAll())
                    .deg(weather.getWind().getDeg())
                    .main(weather.getWeather().get(0).getMain())
                    .description(weather.getWeather().get(0).getDescription())
                    .fillTemp(weather.getMain().getFillTemp())
                    .gust(weather.getWind().getGust())
                    .humidity(weather.getMain().getHumidity())
                    .pressure(weather.getMain().getPressure())
                    .temp(weather.getMain().getTemp())
                    .tempMax(weather.getMain().getTempMax())
                    .tempMin(weather.getMain().getTempMin())
                    .visibility(weather.getVisibility())
                    .windSpeed(weather.getWind().getSpeed())
                    .build());
        });
    }*/


}
