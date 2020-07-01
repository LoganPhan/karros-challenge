package com.example.demo.service.impl;

import com.example.demo.domain.GPS;
import com.example.demo.repository.GpsRepository;
import com.example.demo.service.GPSService;
import com.example.demo.service.dto.GPSDTO;
import com.example.demo.service.dto.mapper.GPSMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GPSServiceImpl implements GPSService {

    @Autowired
    private GpsRepository gpsRepository;

    @Autowired
    private GPSMapper mapper;

    @Override
    public GPSDTO save(GPSDTO dto) {
        GPS entity = mapper.toEntity(dto);
        entity = gpsRepository.save(entity);
        dto = mapper.toDto(entity);
        return dto;

    }

    @Override
    public Page<GPSDTO> getTracks(Pageable pageable) {
        Page<GPS> page = gpsRepository.findAll(pageable);
        Page<GPSDTO> dto = page.map(mapper::toDto);
        return dto;
    }

    @Override
    public GPSDTO getTrackById(Long id) {
       return mapper.toDto(gpsRepository.findOne(id));
    }
}
