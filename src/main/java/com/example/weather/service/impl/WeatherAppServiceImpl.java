package com.example.weather.service.impl;

import com.example.weather.dao.WeatherAppDAO;
import com.example.weather.model.Location;
import com.example.weather.service.WeatherAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Service
public class WeatherAppServiceImpl implements WeatherAppService {

    @Autowired
    private WeatherAppDAO weatherAppDAO ;

    public final static String apiKey = "155337068a0ce199f5897448461fcdc6" ;

    public String getAndStoreCityWeather(String cityName) {
        System.out.println("... Consumes the API response ... for City ... " + cityName);
        String response = processExternalAPI(cityName) ;
        Location location =  createLocation (makeLocationFromCity(cityName)) ;
        System.out.println("\nResponse = " + response);
        return response;
    }

    private String processExternalAPI(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = "http://api.openweathermap.org/data/2.5/weather?";
        String city = cityName ;
        System.out.println("\n\n... Calling the external API for city \"" + city + "\"");
        String finalURL = apiURL + "q=" + city + "&AppID=" + apiKey;
        ResponseEntity<String> response =  restTemplate.getForEntity(finalURL, String.class) ;
        System.out.println("\n URL :: " + finalURL + "\n") ;
        return response.toString() ;
    }


    private Location createLocation(@Valid @RequestBody Location location) {
        System.out.println("Creates an entry into the cityDetails table.");
        return weatherAppDAO.save(location) ;
    }

    private Location makeLocationFromCity(String cityName) {
        Location location = new Location() ;
        location.setCityName(cityName);
        return location ;
    }

    public List<String> getMostlySearchedCities() {
        return weatherAppDAO.findTopFiveCities() ;
    }

    public List<String> getLeastSearchedCities() {
        return weatherAppDAO.findBottomFiveCities() ;
    }

    public List<String> getRecentFiveCities() {
        return weatherAppDAO.findRecentFiveCities() ;
    }
}
