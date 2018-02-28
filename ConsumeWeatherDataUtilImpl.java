package com.data.weather.util.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.data.weather.domain.Weather;
import com.data.weather.util.ConsumeWeatherDataUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Component
public class ConsumeWeatherDataUtilImpl implements ConsumeWeatherDataUtil {

	private String localServiceUrl;
	private Map<String, String> localMap = null;

	public List<Weather> populateAllWeatherStationData(Map<String, String> map) {
		List<Weather> resultList = new ArrayList<Weather>();
		String localUrl = null;
		try {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String result;
				Weather weather = new Weather();
				RestTemplate rest = new RestTemplate();
				localUrl = localServiceUrl.replace("#", entry.getKey());
				String response = rest.getForObject(localUrl, String.class);
				Gson gson = new Gson();
				JsonObject job = gson.fromJson(response, JsonObject.class);
				System.out.println("<--JSON data--->"+job);
				JsonElement tempElement = job.get("cod");
				if (tempElement != null && StringUtils.isNotEmpty(tempElement.toString())) {
					if (tempElement.toString().equals("200")) {
						// coordinate longitude
						JsonElement tempElement0 = job.getAsJsonObject("coord").get("lon");
						if (tempElement0 != null && StringUtils.isNotEmpty(tempElement0.toString())) {
							weather.setLongtitude(tempElement0.toString());
						}
						// coordinate latitude
						JsonElement tempElement1 = job.getAsJsonObject("coord").get("lat");
						if (tempElement1 != null && StringUtils.isNotEmpty(tempElement1.toString())) {
							weather.setLatitude(tempElement1.toString());
						}
						// weather condition
						JsonArray tempArray = job.getAsJsonArray("weather");
						System.out.println("<--tempArray--->"+tempArray);
						JsonObject tempEle = new JsonObject();
						tempEle = (JsonObject) tempArray.get(0);
						System.out.println("<--tempElement--->"+tempEle);
						JsonElement tempElement2 = tempEle.get("description");
						if (tempElement2 != null && StringUtils.isNotEmpty(tempElement2.toString())) {
							weather.setWeatherCondition(tempElement2.toString().replace("\"", ""));
						}
						// temperature
						JsonElement tempElement3 = job.getAsJsonObject("main").get("temp");
						if (tempElement3 != null && StringUtils.isNotEmpty(tempElement3.toString())) {
							Double temp = Double.parseDouble(tempElement3.toString()) - 273;
							DecimalFormat df = new DecimalFormat("0.0");
							String tempStr = df.format(temp);
							weather.setTemperature(tempStr);
						}
						// pressure
						JsonElement tempElement4 = job.getAsJsonObject("main").get("pressure");
						if (tempElement4 != null && StringUtils.isNotEmpty(tempElement4.toString())) {
							weather.setPressure(tempElement4.toString());
						}
						// humidity
						JsonElement tempElement5 = job.getAsJsonObject("main").get("humidity");
						if (tempElement5 != null && StringUtils.isNotEmpty(tempElement5.toString())) {
							weather.setHumidity(tempElement5.toString());
						}
						// wind
						JsonElement tempElement6 = job.getAsJsonObject("wind").get("speed");
						if (tempElement6 != null && StringUtils.isNotEmpty(tempElement6.toString())) {
							weather.setWind(tempElement6.toString());
						}
						//visibility
						JsonElement tempElementVisibility = job.get("visibility");
						System.out.println("<--tempElement7--->"+tempElementVisibility.toString());
						if (tempElementVisibility != null && StringUtils.isNotEmpty(tempElementVisibility.toString())) {
							weather.setVisibility(tempElementVisibility.toString());
						}
						
						
						// date
						Date date = new Date();
						DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
						dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
						String dateText = dateformat.format(date);
						weather.setCurrentDate(dateText);
						// iata city code
						weather.setIataCityCode(entry.getValue());
//						result = weather.toString();
						// create list for all weather station
						resultList.add(weather);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	@Override
	public List<Weather> getAllWeatherStationData() {
		List<Weather> resultList = null;
		try {
			localServiceUrl = ReadPropertiesUtil.getSericeURL();
			System.out.println("<--localServiceUrl--->"+localServiceUrl);
			localMap = ReadPropertiesUtil.getCitiesValues();
			resultList = populateAllWeatherStationData(localMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	@Override
	public Weather getWeatherStationData(String city) {
		Weather resultString = null;
		List<Weather> resultList = null;
		Map<String, String> cityMap = new HashMap<String, String>();
		try {
			localServiceUrl = ReadPropertiesUtil.getSericeURL();
			System.out.println("<--localServiceUrl--->"+localServiceUrl);
			localMap = ReadPropertiesUtil.getCitiesValues();
			String localCity = city.replace(" ", "").toLowerCase();
			if (localMap.containsKey(localCity)) {
				for (Map.Entry<String, String> entry : localMap.entrySet()) {
					if (entry.getKey().equalsIgnoreCase(localCity)) {
						cityMap.put(entry.getKey(), entry.getValue());
					}
				}
				resultList = populateAllWeatherStationData(cityMap);
				resultString = resultList.get(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultString;
	}

}
