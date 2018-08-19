package com.cwoongc.study.jpa_fundmental.composite_key.entity;

import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ChildV3Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(ChildV3Id.class)
@NoArgsConstructor
@Getter
@Setter
public class ChildV3 {


    @Id
    @ManyToOne
    @JoinColumn(name="parent_id")
    public ParentV3 parent;

    @Id
    @Column(name="child_id")
    private String childId;

    private String name;


}
