package com.cwoongc.study.jpa_fundmental.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@Setter
public class MemberV3 {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member.member_id")
    @TableGenerator(name="member.member_id")
    @Column(name="member_id", nullable = false)
    private Long id;

    private String name;

    private String city;

    private String street;

    private String zipcode;
}
