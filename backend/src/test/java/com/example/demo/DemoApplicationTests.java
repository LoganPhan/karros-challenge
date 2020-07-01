package com.example.demo;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.example.demo.domain.GPS;
import com.example.demo.repository.GpsRepository;
import com.example.demo.service.GPSService;
import com.example.demo.service.dto.GPSDTO;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private XmlMapper xmlMapper;

	@Autowired
	private GpsRepository gpsRepository;

	private GPSDTO gpsDto;

	@Autowired
	private GPSService gpsService;
	@Before
	public void init() throws IOException {
		gpsDto = xmlMapper.readValue(new File("sample\\sample.gpx"), GPSDTO.class);
	}

	 public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
	            MediaType.APPLICATION_JSON.getType(),
	            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

	@Test
	public void testUploadApi() throws Exception {
		final MockMultipartFile file = new MockMultipartFile("file", new FileInputStream(new File("sample\\sample.gpx")));
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
				.file(file))
			.andExpect(status().isOk());
		GPS gps = gpsRepository.findAll().get(0);
		assertThat(gps.getId(), notNullValue());
	}

	/**
	 * Test get all tracks with latest-time (default is latest)
	 * @throws Exception
	 */
	@Test
	public void testGetTracks() throws Exception {
		gpsDto = gpsService.save(gpsDto);
		assertThat(gpsRepository.findAll().size(), equalTo(1));
		mockMvc.perform(MockMvcRequestBuilders.get("/tracks?page=0&size=10")
						.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(xpath("//PageImpl//content").exists())
				.andExpect(xpath("//PageImpl//totalElements").number(1.0))
				.andExpect(xpath("//PageImpl//content//metadata//name").string(gpsDto.getMetadata().getName()))
				.andExpect(xpath("//PageImpl//content//wpt").nodeCount(gpsDto.getWaypoints().size()))
				.andExpect(xpath("//PageImpl//content//trk//trkseg//trkpt").nodeCount(gpsDto.getTrack().getTrackpoints().size()));
	}

	/**
	 * Test Tracks by id
	 * @throws Exception
	 */
	@Test
	public void testGetTrackById() throws Exception {
		gpsDto = gpsService.save(gpsDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/tracks/{id}", gpsDto.getId())
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(gpsDto.getId().intValue())))
				.andExpect(jsonPath("$.metadata.name", equalTo(gpsDto.getMetadata().getName())))
				.andExpect(jsonPath("$.waypoints.length()", equalTo(gpsDto.getWaypoints().size())))
				.andExpect(jsonPath("$.track.trackpoints.length()", equalTo(gpsDto.getTrack().getTrackpoints().size())));
	}
}
