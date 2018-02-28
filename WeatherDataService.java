package com.data.weather.service;

import java.util.List;

import com.data.weather.domain.Weather;

public interface WeatherDataService {
	
	List <Weather> getAllWeatherStation();
	Weather getWeatherStationByCity(String city);
}
