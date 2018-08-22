package com.cwoongc.study.jpa_fundmental.embedded_type.entity.value;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Zipcode {
    String zip;
    String plusFour;
}


