/**
 * 
 */
package com.sindelantal.ifood.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.sindelantal.ifood.exception.CityNotFoundException;
import com.sindelantal.ifood.exception.NothingSelectedException;
import com.sindelantal.ifood.model.Track;
import com.sindelantal.ifood.service.IfoodService;

/**
 * Controller
 * 
 * @author Lenin Meza <merolhack@gmail.com>
 */
@RestController
@RequestMapping("/api/v1")
public class IfoodController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IfoodService ifoodService;

	@RequestMapping(value = "/tracks", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public HttpEntity<Track> healthCheck(
			@RequestParam(value = "city", defaultValue = "", required=false) String city, 
			@RequestParam(value = "lat", defaultValue = "", required=false) String lat,
			@RequestParam(value = "lon", defaultValue = "", required=false) String lon) throws RestClientException, CityNotFoundException, NothingSelectedException {
		
		HttpEntity<Track> response = ifoodService.getTracks(city.trim(), lat.trim(), lon.trim());
		
        return response;
    }

}
