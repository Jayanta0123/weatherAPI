package com.example.weather.service;

import java.util.List;

public interface WeatherAppService {
    public String getAndStoreCityWeather(String cityName) ;
    public List<String> getMostlySearchedCities() ;
    public List<String> getLeastSearchedCities() ;
    public List<String> getRecentFiveCities() ;
}
