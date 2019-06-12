package com.sindelantal.ifood.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
	
	@JsonProperty("album_type")
	public String albumType;
	
	public List<Artists> artists;
	
	@JsonProperty("available_markets")
	public List<String> availableMarkets;
	
	@JsonProperty("external_urls")
	public ExternalUrls externalUrls;
	
	public String href;
	
	public String id;
	
	public List<Images> images;
	
	public String name;
	
	@JsonProperty("releaseDate")
	public String release_date;
	
	@JsonProperty("releaseDatePrecision")
	public String release_date_precision;
	
	@JsonProperty("total_tracks")
	public String totalTracks;
	
	public String type;
	
	public String uri;
	
}