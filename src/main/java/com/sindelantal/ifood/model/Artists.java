package com.sindelantal.ifood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artists {

	@JsonProperty("external_urls")
	public ExternalUrls externalUrls;
	
	public String href;
	public String id;
	public String name;
	public String type;
	public String uri;
}
