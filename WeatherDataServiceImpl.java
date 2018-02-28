package com.data.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.weather.domain.Weather;
import com.data.weather.service.WeatherDataService;
import com.data.weather.util.ConsumeWeatherDataUtil;
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	
	@Autowired
	private ConsumeWeatherDataUtil consumeWeatherDataUtil;

	@Override
	public List<Weather> getAllWeatherStation() {
		List<Weather> result = consumeWeatherDataUtil.getAllWeatherStationData();
		return result;
	}

	@Override
	public Weather getWeatherStationByCity(String city) {
		Weather result  = consumeWeatherDataUtil.getWeatherStationData(city);
		return result;
	}

}

