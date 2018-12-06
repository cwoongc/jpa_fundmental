package com.cwoongc.study.jpa_fundmental.criteria.entity;

import com.cwoongc.study.jpa_fundmental.criteria.type.JobType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CrMember {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "cr_member.member_id")
    @TableGenerator(name="cr_member.member_id", pkColumnValue = "cr_member.member_id")
    @Column(name = "member_id")
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private JobType job;

    @Builder
    public CrMember(String name, Integer age, JobType job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    @Builder
    public CrMember(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
