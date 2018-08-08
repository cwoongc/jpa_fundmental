package com.cwoongc.study.jpa_fundmental.member.entity;

import com.cwoongc.study.jpa_fundmental.member.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@ToString
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String username;

    private Integer age;

    //추가
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;



}
