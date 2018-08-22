package com.cwoongc.study.jpa_fundmental.embedded_type.entity.value;

import com.cwoongc.study.jpa_fundmental.embedded_type.entity.PhoneServiceProvider;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PhoneNumber {

    String areaCode;
    String localNumber;

    @ManyToOne
    PhoneServiceProvider provider;

    @Builder
    public PhoneNumber(String areaCode, String localNumber) {
        this.areaCode = areaCode;
        this.localNumber = localNumber;
    }
}
