package com.cwoongc.study.jpa_fundmental.jpql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "members")
@NoArgsConstructor
@Entity
public class JpTeam {


    @Id
    @TableGenerator(name="jp_team.team_id", pkColumnValue = "jp_team.team_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "jp_team.team_id")
    private Long id;

    private String name;

    @OneToMany(
            mappedBy="team"
            ,fetch = FetchType.LAZY
    )
    private List<JpMember> members = new ArrayList<>();

    @Builder
    public JpTeam(String name) {
        this.name = name;
    }


    public void addJpMember(JpMember jpMember) {
        this.members.add(jpMember);
        jpMember.setTeam(this);
    }
}
