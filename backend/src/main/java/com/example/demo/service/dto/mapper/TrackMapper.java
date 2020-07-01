package com.example.demo.service.dto.mapper;

import com.example.demo.domain.Metadata;
import com.example.demo.domain.Track;
import com.example.demo.service.dto.MetadataDTO;
import com.example.demo.service.dto.TrackDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface TrackMapper extends EntityMapper<TrackDTO, Track> {

    default Track fromId(Long id) {
        if (id == null) {
            return null;
        }
        Track track = new Track();
        track.setId(id);
        return track;
    }
}
