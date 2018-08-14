package com.cwoongc.study.jpa_fundmental.member.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class MemberV1 {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;


    private String name;

    private String city;

    private String street;

    private String zipcode;




}
