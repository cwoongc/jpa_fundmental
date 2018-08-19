package com.cwoongc.study.jpa_fundmental.embedded_type.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class MemberV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member.member_id")
    @TableGenerator(name = "member.member_id", pkColumnValue = "member.member_id")
    @Column(name = "member_id")
    private Long id;
    private String name;

    //근무기간
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    //집주소
    private String city;
    private String street;
    private String zipcode;


}
