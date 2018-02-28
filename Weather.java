package com.data.weather.domain;

import org.springframework.stereotype.Component;

@Component
public class Weather {
	private String iataCityCode;
	private String longtitude;
	private String latitude;
	private String currentDate;
	private String weatherCondition;
	private String temperature;
	private String pressure;
	private String humidity;
	private String wind;
	private String visibility;
	
	public String getIataCityCode() {
		return iataCityCode;
	}
	public void setIataCityCode(String iataCityCode) {
		this.iataCityCode = iataCityCode;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getWeatherCondition() {
		return weatherCondition;
	}
	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	@Override
	public String toString() {
		/*return "Weather [iataCityCode=" + iataCityCode + ", longtitude=" + longtitude + ", latitude=" + latitude
				+ ", currentDate=" + currentDate + ", weatherCondition=" + weatherCondition + ", temperature="
				+ temperature + ", pressure=" + pressure + ", humidity=" + humidity + "]";*/
		return "" + iataCityCode + "|" + latitude + "," + longtitude
		+ "|" + currentDate + "|" + weatherCondition + "|"
		+ temperature + "|" + pressure + "|" + humidity +  "|" + wind+  "|" + visibility+"";
		
	}
	
	
}
