package pl.rychlik.weather.mapper;

import org.mapstruct.Mapper;
import pl.rychlik.weather.model.dao.City;
import pl.rychlik.weather.model.dto.CityDto;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDto toDto(City city);
    City toDao(CityDto cityDto);
}
