package com.cwoongc.study.jpa_fundmental.embedded_type.entity.value;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {

    private String city;
    private String street;
    private String state;

    @Embedded
    private Zipcode zipcode;

    @Builder
    public Address(String city, String street, String state, Zipcode zipcode) {
        this.city = city;
        this.street = street;
        this.state = state;
        this.zipcode = zipcode;
    }
}
