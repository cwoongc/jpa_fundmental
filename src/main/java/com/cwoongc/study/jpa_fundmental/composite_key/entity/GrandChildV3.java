package com.cwoongc.study.jpa_fundmental.composite_key.entity;

import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.GrandChildV3Id;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(GrandChildV3Id.class)
public class GrandChildV3 {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="parent_id"),
            @JoinColumn(name="child_id")
    })
    private ChildV3 child;

    @Id
    @Column(name="grandchild_id")
    private String id;

    private String name;
}
