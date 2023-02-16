package sniaper.project.MySimpleProjectParserForWork;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


@Component("cityBean")
@Scope("singleton")
public class City implements CoordinateData{

	private double xcoord;
	private double ycoord;
	private String name;
	
	public double getXcoord() {
		return xcoord;
	}
	
	public double getYcoord() {
		return ycoord;
	}
	
	public City() {
	}
	
	@Override
	public void setCoords(double xcoord, double ycoord) {
		this.xcoord = xcoord;
		this.ycoord = ycoord;
	}

	@Override
	public String generationNameCity() {
		try {
			String addressNameCity = "https://nominatim.openstreetmap.org/reverse?format=json&lat="
					+ this.xcoord + "&lon=" + this.ycoord;
			
			switch (Detective.getStatus()) {case Y:System.out.println(addressNameCity);
			default:
				break;}
			
			HttpResponse<String> jsonStringCity = Unirest.get(addressNameCity)
					.asString();
			
			JSONObject objCiti = new JSONObject(jsonStringCity.getBody());
			JSONObject nameCity = objCiti.getJSONObject("address");
			
			getNameCity(nameCity);
			return new String(name.getBytes(), "cp1251");
		} catch (UnirestException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Double> getCoordinateParameters() {
		return new ArrayList<Double>(Arrays.asList(xcoord, ycoord));
	}
	
	private void getNameCity(JSONObject data) {
		
		if(data.has("city")) {
			this.name = strSetNameCity("city", data);
		}
		else if (data.has("village")) {
			this.name = strSetNameCity("village", data);
		}
		else if (data.has("county")) {
			this.name = strSetNameCity("county", data);
		}
		else {
			this.name = "Данные не обнаружены";
		}
	}
	
	private String strSetNameCity(String state, JSONObject obj) {
		return String.format("%s, %s", obj.getString("state"), obj.getString(state));
	}

}
