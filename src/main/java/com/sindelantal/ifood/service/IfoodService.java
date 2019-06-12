package com.sindelantal.ifood.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.sindelantal.ifood.exception.CityNotFoundException;
import com.sindelantal.ifood.exception.NothingSelectedException;
import com.sindelantal.ifood.model.Track;
import com.sindelantal.ifood.model.Weather;

@Service
public class IfoodService implements IfoodInterface {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WeatherService weatherService;
	
	@Autowired
	SpotifyService spotifyService;

	/**
	 * @param city
	 * @param lat
	 * @param lon
	 * @return List<Track> List with each Track or empty list
	 * @throws CityNotFoundException 
	 * @throws RestClientException 
	 * @throws NothingSelectedException 
	 */
	public HttpEntity<Track> getTracks(String city, String lat, String lon) throws RestClientException, CityNotFoundException, NothingSelectedException {
		Double temperature;
		String genre = "";
		if (!"".equals(city)) {
			LOG.info("Find by the city {}", city);
			ResponseEntity<Weather> weather = weatherService.findByCity(city);
			LOG.info("weather temperature: {}", weather.getBody().main.temp);
			// Get the temperature
			temperature = weather.getBody().main.temp;
		} else if (!"".equals(lat) && !"".equals(lon)) {
			LOG.info("Find by the lat {} & lon {}", lat, lon);
			ResponseEntity<Weather> weather = weatherService.findByCoordinates(
					Double.parseDouble(lat), 
					Double.parseDouble(lon)
				);
			LOG.info("weather temperature: {}", weather.getBody().main.temp);
			temperature = weather.getBody().main.temp;
		} else {
			LOG.info("Nothing selected (city,lat,lon)");
			throw new NothingSelectedException("Nothing selected (city,lat,lon)");
		}
		// Get the type of music based on the temperature
		if (temperature < 10) {
			genre = "blues";
		} else if (temperature >= 10 && temperature <= 14) {
			genre = "bossanova";
		} else if (temperature > 14 && temperature <= 30) {
			genre = "reggae";
		} else {
			genre = "salsa";
		}
		return spotifyService.getSongs(genre);
	}

}
