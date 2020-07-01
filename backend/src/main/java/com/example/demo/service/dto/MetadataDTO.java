package com.example.demo.service.dto;

import java.time.Instant;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class MetadataDTO  {
	
	@JacksonXmlProperty
	private String name;
	
	@JacksonXmlProperty
	private String desc;
	
	@JacksonXmlProperty
	private LinkDTO link;
	
	@JacksonXmlProperty
	private Instant time;
	
	@JacksonXmlProperty
	private String author;
	
	public LinkDTO getLink() {
		return link;
	}
	public void setLink(LinkDTO link) {
		this.link = link;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}
}
