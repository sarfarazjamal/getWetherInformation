package com.data.weather.util;

import java.util.List;
import java.util.Map;

import com.data.weather.domain.Weather;

public interface ConsumeWeatherDataUtil {
	
	List<Weather> getAllWeatherStationData();
	Weather getWeatherStationData(String city);

}
