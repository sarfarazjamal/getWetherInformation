package com.data.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.weather.domain.Weather;
import com.data.weather.service.WeatherDataService;

@RestController
@RequestMapping("weatherdata")
public class WeatherDataController {

	@Autowired
	private WeatherDataService weatherDataService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain")
	public String getMessage() {
		return "Welcome !!, Use contextroot/all to get all weather data or contextroot/<city name> for specific city as JSON ";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Weather> getWeatherDataAllInJSON() {
		List<Weather> response = weatherDataService.getAllWeatherStation();
		return response;
	}
	
	@RequestMapping(value =  "/{name}", method = RequestMethod.GET, produces = "application/json")
	public Weather getWeatherDataInJSON(@PathVariable String name) {
		Weather response = weatherDataService.getWeatherStationByCity(name);
		return response;
	}
	
}
