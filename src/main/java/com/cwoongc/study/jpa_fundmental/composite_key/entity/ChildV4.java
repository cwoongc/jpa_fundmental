package com.cwoongc.study.jpa_fundmental.composite_key.entity;


import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ChildV4Id;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ChildV4 {

    @EmbeddedId
    private ChildV4Id id;

    @MapsId("parentId") //ChildV4Id.parentId 매핑
    @ManyToOne
    @JoinColumn(name="parent_id")
    public ParentV4 parent;

    private String name;
}
