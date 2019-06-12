package com.sindelantal.ifood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyService implements SpotifyInterface {
	@Autowired
	private RestTemplate restTemplate;
	
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
