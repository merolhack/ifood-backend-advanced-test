package com.sindelantal.ifood.service;

import org.springframework.http.HttpHeaders;

public interface SpotifyInterface {
	
	/**
	 * Get the headers for the request
	 *
	 * @return HttpHeaders headers
	 */
	public HttpHeaders getHeaders();

}
