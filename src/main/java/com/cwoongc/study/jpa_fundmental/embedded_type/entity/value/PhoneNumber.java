package com.cwoongc.study.jpa_fundmental.embedded_type.entity.value;

import com.cwoongc.study.jpa_fundmental.embedded_type.entity.PhoneServiceProvider;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {

    String areaCode;
    String localNumber;

    @ManyToOne
    PhoneServiceProvider provider;
}
