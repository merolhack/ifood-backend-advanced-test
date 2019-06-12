package com.sindelantal.ifood.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
	public Coord coord;
	public List<WeatherDetails> weather;
	public String base;
	public Main main;
	public Integer visibility;
	public Wind wind;
	public Clouds clouds;
	public Integer dt;
	public Sys sys;
	public Integer timezone;
	public Integer id;
	public String name;
	public String cod;
}
