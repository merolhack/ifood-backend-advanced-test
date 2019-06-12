package com.sindelantal.ifood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Seed {
	public Integer initialPoolSize;
	public Integer afterFilteringSize;
	public Integer afterRelinkingSize;
	public String id;
	public String type;
	public String href;
}
