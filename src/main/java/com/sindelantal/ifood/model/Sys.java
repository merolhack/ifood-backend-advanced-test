package com.sindelantal.ifood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {
	public Integer type;
	public Integer id;
	public Double message;
	public String country;
	public Integer sunrise;
	public Integer sunset;
}
