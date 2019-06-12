package com.sindelantal.ifood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken {
	
	@JsonProperty("access_token")
	public String accessToken;
	
	@JsonProperty("token_type")
	public String tokenType;
	
	@JsonProperty("expires_in")
	public String expiresIn;
	
	public String scope;
}
