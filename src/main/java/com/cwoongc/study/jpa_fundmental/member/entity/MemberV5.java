package com.cwoongc.study.jpa_fundmental.member.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="party")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AttributeOverrides({
//        @AttributeOverride(name = "id", column = @Column(name="member_id")),
        @AttributeOverride(name = "name", column = @Column(name="member_name"))
})
public class MemberV5 extends PartyV5 {

    private String email;

    @Builder
    public MemberV5(Long creator,  String name, String email) {
        super.setCreator(creator);
        super.setUpdater(creator);
        super.setName(name);
        this.email = email;
    }
}
