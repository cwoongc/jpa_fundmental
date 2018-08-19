package com.cwoongc.study.jpa_fundmental.composite_key.entity;

import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ParentV1Id;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Setter
@Entity
@IdClass(ParentV1Id.class)
public class ParentV1 {

    @Id
    @Column(name="parent_id1")
    private String id1;

    @Id
    @Column(name="parent_id2")
    private String id2;

    private String name;
}
