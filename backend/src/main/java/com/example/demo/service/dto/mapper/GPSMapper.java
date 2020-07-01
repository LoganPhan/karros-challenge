package com.example.demo.service.dto.mapper;

import com.example.demo.domain.GPS;
import com.example.demo.service.dto.GPSDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MetadataMapper.class})
public interface GPSMapper extends EntityMapper<GPSDTO, GPS> {

    @Mapping(target = "tracks", source = "dto.track.trackpoints")
    GPS toEntity(GPSDTO dto);

    @Mapping(target = "track.trackpoints", source = "tracks")
    GPSDTO toDto(GPS entity);

    default GPS fromId(Long id) {
        if (id == null) {
            return null;
        }
        GPS gps = new GPS();
        gps.setId(id);
        return gps;
    }
}
