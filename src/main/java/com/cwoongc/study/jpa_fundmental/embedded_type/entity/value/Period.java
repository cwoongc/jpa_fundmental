package com.cwoongc.study.jpa_fundmental.embedded_type.entity.value;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class Period {

    @Temporal(TemporalType.DATE) private Date startDate;

    @Temporal(TemporalType.DATE) private Date endDate;

    public boolean isWork(Date date) {
        Date now = new Date();

        return (now.equals(startDate) || now.after(startDate))
                && (now.equals(endDate) || now.before(endDate))
                ? true : false;
    }
}
