package com.sindelantal.ifood.service;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sindelantal.ifood.model.AccessToken;
import com.sindelantal.ifood.model.Track;

@Service
public class SpotifyService implements SpotifyInterface {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${spotify.token.url}")
	private String tokenUrl;

	@Value("${spotify.token.user}")
	private String tokenUser;

	@Value("${spotify.token.pass}")
	private String tokenPass;

	@Value("${spotify.api.recommendations}")
	private String apiRecommendations;

	public Track getSongs(String genre) {
		// Sets a new instance
		RestTemplate restTemplate = new RestTemplate();
		// Gets the access token
		String accessToken = this.getAccessToken();
		HttpHeaders headers = this.getHeaders(accessToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<Track> trackList = null;
		LOG.info("headers: {}", headers.toString());
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiRecommendations)
		        .queryParam("seed_genres", genre);
		LOG.info("Builder {}", builder.toUriString());
		LOG.info("seed_genres: {}", genre);
		LOG.info("apiRecommendations: {}", apiRecommendations);

		try {
			trackList = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Track.class);
		} catch (HttpClientErrorException e) {
			LOG.info("Message... {}", e.getMessage());
			LOG.info("Body... {}", e.getResponseBodyAsString());
			LOG.info("Headers... {}", e.getResponseHeaders());
		}

		LOG.info("trackList: {}", trackList);

		return trackList.getBody();
	}

	/**
	 * Get the access token
	 */
	public String getAccessToken() {
		String accessToken = null;
		HttpHeaders authHeaders = this.getAuthHeaders(tokenUser, tokenPass);
		LOG.info("authHeaders: {}", authHeaders);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "client_credentials");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, authHeaders);
		LOG.info("entity: {}", request.toString());

		try {
			HttpEntity<AccessToken> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request,
					AccessToken.class);
			LOG.info("accessToken: {}", response.getBody().accessToken);
			accessToken = response.getBody().accessToken;
		} catch (HttpClientErrorException e) {
			LOG.info("Message... {}", e.getMessage());
			LOG.info("Body... {}", e.getResponseBodyAsString());
			LOG.info("Headers... {}", e.getResponseHeaders());
		}
		
		return accessToken;
	}

	/**
	 * Gets the Authorization: Basic header
	 * 
	 * @return HttpHeaders headers
	 */
	public HttpHeaders getAuthHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

	/**
	 * Get the headers for the request
	 *
	 * @return HttpHeaders headers
	 */
	public HttpHeaders getHeaders(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer " + token);
		return headers;
	}

}
