package com.example.demo.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class LatLon extends Auditing {

    @Column(name = "lat", precision = 20, scale = 16)
    private BigDecimal lat;

    @Column(name = "lon", precision = 20, scale = 16)
    private BigDecimal lon;
    
    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

}
