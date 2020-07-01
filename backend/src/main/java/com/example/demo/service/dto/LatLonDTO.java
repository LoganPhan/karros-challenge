package com.example.demo.service.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.math.BigDecimal;
import java.time.Instant;

@JacksonXmlRootElement
public class LatLonDTO {

	@JacksonXmlProperty(isAttribute = true)
	private BigDecimal lat;

	@JacksonXmlProperty(isAttribute = true)
	private BigDecimal lon;

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
}
