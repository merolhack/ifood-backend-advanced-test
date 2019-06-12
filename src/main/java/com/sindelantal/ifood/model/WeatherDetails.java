package com.sindelantal.ifood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetails {
	public Integer id;
	public String main;
	public String description;
	public String icon;
} 
