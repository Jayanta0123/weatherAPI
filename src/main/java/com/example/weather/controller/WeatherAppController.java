package com.example.weather.controller;

import com.example.weather.service.WeatherAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    This class contains the main App-interfaces, which the user can access from front-end
        - Search for current weather of a city
        - Least 5 cities searched for
        - Most 5 cities searched for
        - Recent 5 cities searched for
 */

@RestController
@RequestMapping ("v1/weather")
public class WeatherAppController {

    @Autowired
    private  WeatherAppService weatherAppService ;

    @GetMapping("/city/{name}")
    public String getAndStoreWeather(@PathVariable (value = "name") String cityName) {
        return weatherAppService.getAndStoreCityWeather(cityName) ;
    }

    @GetMapping("/mostly_referred_cities")
    public List<String> getTopFiveCities() {
        return weatherAppService.getMostlySearchedCities() ;
    }

    @GetMapping("/least_referred_cities")
    public List<String> getBottomFiveCities() {
        return weatherAppService.getLeastSearchedCities() ;
    }

    @GetMapping("/recently_referred_cities")
    public List<String> getRecentFiveCities() {
        return weatherAppService.getRecentFiveCities() ;
    }
}
