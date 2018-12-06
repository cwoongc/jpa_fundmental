package com.cwoongc.study.jpa_fundmental.jpql.entity.value;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {

    private String city;
    private String steet;
    private String state;

    @Builder
    public Address(String city, String steet, String state) {
        this.city = city;
        this.steet = steet;
        this.state = state;
    }
}
