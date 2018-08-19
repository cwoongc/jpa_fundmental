package com.cwoongc.study.jpa_fundmental.composite_key.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Non-Identifying 맵핑
 */
@Entity
@Getter
@Setter
public class ChildV1 {

    @Id
    private String id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="parent_id1", referencedColumnName = "parent_id1"),
            @JoinColumn(name="parent_id2", referencedColumnName = "parent_id2")
    })
    private ParentV1 parent;


}
