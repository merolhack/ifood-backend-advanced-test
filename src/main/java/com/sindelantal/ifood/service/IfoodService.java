package com.sindelantal.ifood.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.sindelantal.ifood.exception.CityNotFoundException;
import com.sindelantal.ifood.model.Track;
import com.sindelantal.ifood.model.Weather;

@Service
public class IfoodService implements IfoodInterface {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WeatherService weatherService;

	/**
	 * @param city
	 * @param lat
	 * @param lon
	 * @return List<Track> List with each Track or empty list
	 * @throws CityNotFoundException 
	 * @throws RestClientException 
	 */
	public List<Track> getTracks(String city, String lat, String lon) throws RestClientException, CityNotFoundException {
		if (!"".equals(city)) {
			LOG.info("city {}", city);
			ResponseEntity<Weather> weather = weatherService.findByCity(city);
			LOG.info("weather {}", weather.getBody());
		} else if (!"".equals(lat) && !"".equals(lon)) {
			LOG.info("lat {} lon {}", lat, lon);
			ResponseEntity<Weather> weather = weatherService.findByCoordinates(
					Double.parseDouble(lat), 
					Double.parseDouble(lon)
				);
		} else {
			LOG.info("Nothing selected");
		}
		return null;
	}

}
