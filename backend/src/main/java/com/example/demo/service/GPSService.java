package com.example.demo.service;

import com.example.demo.service.dto.GPSDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GPSService {
    /**
     * store GPSDTO into Db
     * @param dto
     * @return
     */
    GPSDTO save(GPSDTO dto);

    /**
     * get tracks
     * @param pageable
     * @return
     */
    Page<GPSDTO> getTracks(Pageable pageable);

    /**
     * get track detail
     * @param id
     * @return
     */
    GPSDTO getTrackById(Long id);
}
