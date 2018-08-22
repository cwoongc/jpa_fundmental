package com.cwoongc.study.jpa_fundmental.embedded_type.entity;

import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.Address;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.Period;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.PhoneNumber;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MemberV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member.member_id")
    @TableGenerator(name = "member.member_id", pkColumnValue = "member.member_id")
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded Period workPeriod;
    @Embedded Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "company_city")),
            @AttributeOverride(name = "street", column = @Column(name = "company_street")),
            @AttributeOverride(name = "state", column = @Column(name = "company_state")),
            @AttributeOverride(name = "zipcode.zip", column = @Column(name = "company_zipcode")),
            @AttributeOverride(name = "zipcode.plusFour", column = @Column(name = "company_plusFour"))
    })
    Address companyAddress;


    @Embedded PhoneNumber phoneNumber;


    @Builder
    public MemberV2(String name, Period workPeriod, Address homeAddress, Address companyAddress, PhoneNumber phoneNumber) {
        this.name = name;
        this.workPeriod = workPeriod;
        this.homeAddress = homeAddress;
        this.companyAddress = companyAddress;
        this.phoneNumber = phoneNumber;
    }



}
