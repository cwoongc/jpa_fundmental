package com.cwoongc.study.jpa_fundmental.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="MEMBER")
public class MemberM_1 {

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String name;

    @Column(name="AGE")
    private Integer age;

    @ManyToOne(
            fetch = FetchType.LAZY
//            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "TEAM_ID")
    private Team team;





}
