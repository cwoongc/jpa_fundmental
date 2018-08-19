package com.cwoongc.study.jpa_fundmental.common.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditAttributesInheritor {

    private Long creator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Date createDatetime;

    private Long updater;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateDatetime;
}
