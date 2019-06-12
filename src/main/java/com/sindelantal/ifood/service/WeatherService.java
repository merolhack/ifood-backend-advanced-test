package com.sindelantal.ifood.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sindelantal.ifood.exception.CityNotFoundException;
import com.sindelantal.ifood.model.Track;
import com.sindelantal.ifood.model.Weather;

@Service
public class WeatherService implements WeatherInterface {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${openweathermap.id}")
	private String id;
	
	@Value("${openweathermap.appId}")
	private String appId;

	@Value("${openweathermap.url}")
	private String owmUrl;
	
	/**
	 * @param cityName
	 * @return List<Track> List with each Track or empty list
	 * @throws CityNotFoundException 
	 * @throws RestClientException 
	 */
	public ResponseEntity<Weather> findByCity(String city) throws CityNotFoundException, RestClientException {
		LOG.info("appId {}", appId);
		LOG.info("owmUrl {}", owmUrl);
		ResponseEntity<Weather> response = null;
		HttpEntity<String> entity = new HttpEntity<>(this.getHeaders());
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(owmUrl)
		        .queryParam("id", id)
		        .queryParam("APPID", appId)
		        .queryParam("q", city)
		        .queryParam("units", "metric");
		LOG.info("Builder {}", builder.toUriString());
		
		try {
			response = restTemplate
					  .exchange(builder.toUriString(), HttpMethod.GET, entity, Weather.class);
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new CityNotFoundException(city + " not Found");
			}
		}
		
		LOG.info(response.toString());
		return response;
		
	}
	
	/**
	 * @param lat, lon
	 * @return Weather List with each Track or empty list
	 */
	public ResponseEntity<Weather> findByCoordinates(double lat, double lon) {
		LOG.info("findByCoordinates");
		HttpEntity<String> entity = new HttpEntity<>(this.getHeaders());
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(owmUrl)
		        .queryParam("id", id)
		        .queryParam("APPID", appId)
		        .queryParam("lat", lat)
		        .queryParam("lon", lon)
		        .queryParam("units", "metric");
		ResponseEntity<Weather> response = restTemplate
				  .exchange(builder.toUriString(), HttpMethod.GET, entity, Weather.class);
		LOG.info(response.toString());
		return null;
		
	}
	
	/**
	 * Get the headers for the request
	 *
	 * @return HttpHeaders headers
	 */
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}
