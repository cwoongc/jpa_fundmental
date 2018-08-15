package com.cwoongc.study.jpa_fundmental.member.entity;

import com.cwoongc.study.jpa_fundmental.order.entity.OrderV2;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Getter
@Setter
public class MemberV2 {

    @Id
    @TableGenerator(name="member.member_id", pkColumnValue = "member.member_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member.member_id")
    @Column(name = "MEMBER_ID")
    private Long id;


    private String name;

    private String city;

    private String street;

    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<OrderV2> orders = new ArrayList<>();



}
