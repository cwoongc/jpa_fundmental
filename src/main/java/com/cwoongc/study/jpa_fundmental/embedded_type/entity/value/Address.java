package com.cwoongc.study.jpa_fundmental.embedded_type.entity.value;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@Setter
public class Address {

    private String city;
    private String street;
    private String state;

    @Embedded
    private Zipcode zipcode;
}
