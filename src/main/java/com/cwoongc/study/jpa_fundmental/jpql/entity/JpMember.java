package com.cwoongc.study.jpa_fundmental.jpql.entity;

import com.cwoongc.study.jpa_fundmental.jpql.entity.value.Address;
import lombok.*;

import javax.persistence.*;

@Entity(name="JpMember")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class JpMember {


    @Id
    @TableGenerator(name="jp_member.member_id", pkColumnValue = "jp_member.member_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "jp_member.member_id")
    private Long id;

    private String name;

    private Integer age;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name="team_id")
    private JpTeam team;


    @Builder
    public JpMember(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Builder
    public JpMember(String name, Integer age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Builder
    public JpMember(String name, Integer age, Address address, JpTeam team) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.team = team;
    }
}
