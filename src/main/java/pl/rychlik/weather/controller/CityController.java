package pl.rychlik.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rychlik.weather.mapper.CityMapper;
import pl.rychlik.weather.model.dto.CityDto;
import pl.rychlik.weather.service.CityService;

@RequiredArgsConstructor
@RequestMapping("/api/city")
@RestController
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @PostMapping
    public CityDto saveCity(@RequestBody CityDto cityDto){
        return cityMapper.toDto(cityService.save(cityMapper.toDao(cityDto)));
    }
}
