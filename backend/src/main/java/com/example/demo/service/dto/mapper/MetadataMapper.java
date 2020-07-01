package com.example.demo.service.dto.mapper;

import com.example.demo.domain.Metadata;
import com.example.demo.domain.Waypoint;
import com.example.demo.service.dto.MetadataDTO;
import com.example.demo.service.dto.WaypointDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {})
public interface MetadataMapper extends EntityMapper<MetadataDTO, Metadata> {

    @Mapping(target = "linkHref", source = "dto.link.href")
    @Mapping(target = "linkText", source = "dto.link.text")
    Metadata toEntity(MetadataDTO dto);

    @Mapping(target = "link.href", source = "entity.linkHref")
    @Mapping(target = "link.text", source = "entity.linkText")
    MetadataDTO toDto(Metadata entity);

    default Metadata fromId(Long id) {
        if (id == null) {
            return null;
        }
        Metadata metadata = new Metadata();
        metadata.setId(id);
        return metadata;
    }

}
