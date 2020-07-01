package com.example.demo.service.dto.mapper;

import com.example.demo.domain.Waypoint;
import com.example.demo.service.dto.WaypointDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface WaypointMapper extends EntityMapper<WaypointDTO, Waypoint> {

    default Waypoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        Waypoint waypoint = new Waypoint();
        waypoint.setId(id);
        return waypoint;
    }
}
