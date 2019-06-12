package com.sindelantal.ifood.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.sindelantal.ifood.exception.CityNotFoundException;
import com.sindelantal.ifood.model.Weather;

public interface WeatherInterface {
	/**
	 * Get the weather by Name
	 *
	 * @param String city
	 * @return Weather List with each Track or empty list
	 * @throws CityNotFoundException 
	 */
	public ResponseEntity<Weather> findByCity(String city) throws CityNotFoundException;
	
	/**
	 * Get the weather by coordinates
	 *
	 * @param Double lat
	 * @param Double lon
	 * @return Weather List with each Track or empty list
	 */
	public ResponseEntity<Weather> findByCoordinates(double lat, double lon) throws CityNotFoundException;
	
	/**
	 * Get the headers for the request
	 *
	 * @return HttpHeaders headers
	 */
	public HttpHeaders getHeaders();
}
