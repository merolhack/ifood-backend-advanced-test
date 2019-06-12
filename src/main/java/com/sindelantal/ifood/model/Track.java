package com.sindelantal.ifood.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
	
	public List<Tracks> tracks;
	public List<Seed> seeds;
}
