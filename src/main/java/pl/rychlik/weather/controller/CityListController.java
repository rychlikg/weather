package pl.rychlik.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rychlik.weather.mapper.CityMapper;
import pl.rychlik.weather.model.dto.CityDto;
import pl.rychlik.weather.repository.CityRepository;
import pl.rychlik.weather.service.CityService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/cityList")
@RestController
public class CityListController {
    private final CityService cityService;
    private final CityMapper cityMapper;
    private final CityRepository cityRepository;

    @GetMapping
    public List<CityDto> list(){
        List<CityDto> cities = new ArrayList<>();

        cityRepository.findAll().forEach(city -> {
            cities.add(cityMapper.toDto(city));
        });
        return cities;
    }
}
