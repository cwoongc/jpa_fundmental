package com.cwoongc.study.jpa_fundmental.embedded_type.entity.value;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Zipcode {
    String zip;
    String plusFour;
}


