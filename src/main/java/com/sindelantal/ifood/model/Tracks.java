package com.sindelantal.ifood.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracks {
	
	public Album album;
	public List<Artists> artists;
	
	@JsonProperty("available_markets")
	public List<String> availableMarkets;
	
	@JsonProperty("disc_number")
	public Integer discNumber;
	
	@JsonProperty("duration_ms")
	public Integer durationMs;
	
	
	public Boolean explicit;
	
	@JsonProperty("external_ids")
	public ExternalIds externalIds;
	
	@JsonProperty("external_urls")
	public ExternalUrls externalUrls;
	
	public String href;
	public String id;
	
	@JsonProperty("is_local")
	public String isLocal;
	
	public String name;
	public String type;
	public String popularity;
	
	@JsonProperty("preview_url")
	public String previewUrl;
	
	@JsonProperty("track_number")
	public String trackNumber;
	
	public String uri;
	
}
