package com.sindelantal.ifood.service;

import java.util.List;

import org.springframework.web.client.RestClientException;

import com.sindelantal.ifood.exception.CityNotFoundException;
import com.sindelantal.ifood.model.Track;

public interface IfoodInterface {
	
	/**
	 * @param city
	 * @return List<Track> List with each Track or empty list
	 * @throws CityNotFoundException 
	 * @throws RestClientException 
	 */
	public List<Track> getTracks(String city, String lat, String lon) throws RestClientException, CityNotFoundException;

}
