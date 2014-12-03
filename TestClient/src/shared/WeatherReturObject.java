package shared;

import java.util.ArrayList;

public class WeatherReturObject {
	private ArrayList<Forecast> weather = new ArrayList<Forecast>();

	public ArrayList<Forecast> getWeather() {
		return weather;
	}

	public void setWeather(ArrayList<Forecast> weather) {
		this.weather = weather;
	}
}
