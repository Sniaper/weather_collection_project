package sniaper.project.MySimpleProjectParserForWork;

import java.io.UnsupportedEncodingException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.stream.IntStream;

import javax.annotation.PreDestroy;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


/**
* @author Sniaper
*/

@Component("DetectedBean")
@Scope("singleton")
public class Detective extends Searching{

	private City city;
	private final String TOKEN = "24b698970ad7223c6f6d4563fe18ce2d";
	private HttpResponse<String> obj;
	private double[] temp;
	private static int countRequestsName;
	private static EnumStatusAddInfo status = EnumStatusAddInfo.N ;
	
	@Value("${name.name}")
	private String name;
	
	
	public City getCity() {
		return city;
	}

	@Autowired
	public Detective(@Qualifier("cityBean") City city) {
		this.city = city;
	}
	
	public void setCoordsCity(double xcoord, double ycoord) {
		city.setCoords(xcoord, ycoord);
	}
	
	@Override
	public void genInfo() {
		responseAPIForDataWeather();
		getCurrentWeather();
	}

	@Override
	public void showInfo() {
		System.out.println(this.city.generationNameCity());
		generationWeatherDaily();
	}

	private void responseAPIForDataWeather() {
		ArrayList<Double> coordinates = city.getCoordinateParameters();
		try {
			String addressWeather = "https://api.openweathermap.org/data/2.5/onecall?lat="
					+ coordinates.get(0) + "&lon="
					+ coordinates.get(1) + "&appid="
					+ TOKEN + "&units=metric&lang=ru";
			
			switch (status) {case Y:System.out.println(addressWeather);
			default:
				break;}
			
			obj = Unirest.get(addressWeather)
					.asString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}
	
	private void getCurrentWeather() {
		JSONObject mainObj = new JSONObject(obj.getBody());
		JSONArray daily = mainObj.getJSONArray("daily");
		
		double[] arrTempDaily = IntStream.range(0, daily.length()).mapToDouble(i -> (Double) daily.getJSONObject(i).getJSONObject("temp").getDouble("min")).toArray();

		this.temp = arrTempDaily;
	}
	
	private void generationWeatherDaily() {
		JSONObject mainObj = new JSONObject(obj.getBody());
		Instant time = Instant.ofEpochSecond(mainObj.getJSONObject("current").getLong("dt") + mainObj.getInt("timezone_offset"));

		
		for (int i = 0; i < this.temp.length; ++i) {
			try {
				System.out.println(String.format(new String("Температура на %s - (%.2f)".getBytes(), "cp1251"), time.plusSeconds(86400 * i), temp[i]));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getNameDetective() {
		System.out.println(name);
	}
	
	public static void addRequest() {
		countRequestsName += 1;
	}
	
	public static void getCountRequestsNameDetector() {
		System.out.println(countRequestsName);
	}
	
	public void setStatus(EnumStatusAddInfo status) {
		Detective.status = status;
	}
	
	public static EnumStatusAddInfo getStatus() {
		return status;
	}
	
	@PreDestroy
	public void closeApp() {
		System.out.println("Close this App");
	}
}

