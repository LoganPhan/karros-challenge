package com.example.demo.service.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown=true)
@JacksonXmlRootElement(localName = "gpx")
public class GPSDTO{

	private Long id;

	@JacksonXmlProperty
	private MetadataDTO metadata;

	@JacksonXmlProperty(localName="wpt")
	@JacksonXmlElementWrapper(useWrapping = false)
	private Collection<WaypointDTO> waypoints;

	@JacksonXmlProperty(localName="trk")
	private TrackDTO track;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public MetadataDTO getMetadata() {
		return metadata;
	}
	public void setMetadata(MetadataDTO metadata) {
		this.metadata = metadata;
	}
	public Collection<WaypointDTO> getWaypoints() {
		return waypoints;
	}
	public void setWaypoints(Collection<WaypointDTO> waypoints) {
		this.waypoints = waypoints;
	}
	public TrackDTO getTrack() {
		return track;
	}
	public void setTrack(TrackDTO track) {
		this.track = track;
	}

}
