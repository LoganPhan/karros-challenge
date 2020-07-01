package com.example.demo.service.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class TrackDTO {

	@JacksonXmlElementWrapper(localName = "trkseg")
	@JacksonXmlProperty(localName = "trkpt")
	private Collection<TrackpointDTO> trackpoints;
	
	public Collection<TrackpointDTO> getTrackpoints() {
		return trackpoints;
	}

	public void setTrackpoints(Collection<TrackpointDTO> trackpoints) {
		this.trackpoints = trackpoints;
	}


}
