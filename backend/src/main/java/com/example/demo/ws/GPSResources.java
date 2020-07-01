package com.example.demo.ws;

import java.io.IOException;

import com.example.demo.common.Constant;
import com.example.demo.service.GPSService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.dto.GPSDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import static org.springframework.data.domain.Sort.Direction;
import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
public class GPSResources {

	private final Logger log = LoggerFactory.getLogger(GPSResources.class);
	
	@Autowired
	private XmlMapper xmlMapper;

	@Autowired
	private GPSService gpsService;

	/**
	 * {@code POST  /upload} : upload gps file
	 *
	 * @param file which the requested should match.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} in body.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@ApiOperation(notes = "Upload file .gpx", value = "", nickname = "tracks")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure")})
	@PostMapping("/upload")
	public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file) throws JsonParseException, JsonMappingException, IOException {
		log.debug("Request upload file");
		GPSDTO dto = xmlMapper.readValue(file.getBytes(), GPSDTO.class);
		 gpsService.save(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * {@code Get  /tracks} : get latest-track
	 *
	 * @param pageable {@link Pageable}
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} in body.
	 */
	@ApiOperation(notes = "Return tracks with pagination", value = "Return all tracks", nickname = "tracks")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure")})
	@GetMapping(name = "/tracks", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Page<GPSDTO>> getTracks(Pageable pageable){
		log.debug("Request list latest-tracks ");
		if(ObjectUtils.isEmpty(pageable.getSort())){
			pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),
					new Sort(DESC, Constant.LAST_MODIFIED_DATE_FIELD));
		}
		return  ResponseEntity.status(HttpStatus.OK).body(gpsService.getTracks(pageable));
	}

	/**
	 * {@code Get  /tracks/{id} : get latest-track
	 *
	 * @param id
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} in body.
	 */
	@ApiOperation(notes = "Find track by track_id", value = "Return single track", nickname = "tracks")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure")})
	@GetMapping("/tracks/{id}")
	public ResponseEntity<GPSDTO> getTrackDetail(@PathVariable("id") Long id){
		log.debug("Request get track detail");
		return  ResponseEntity.status(HttpStatus.OK).body(gpsService.getTrackById(id));
	}
}
