package com.sindelantal.ifood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
	public double temp;
	public double pressure;
	public double humidity;
	public double temp_min;
	public double temp_max;
}
