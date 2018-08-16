package com.cwoongc.study.jpa_fundmental.member.entity;

import com.cwoongc.study.jpa_fundmental.order.entity.OrderV3;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "member")
    private List<OrderV3> orders = new ArrayList<>();
}
