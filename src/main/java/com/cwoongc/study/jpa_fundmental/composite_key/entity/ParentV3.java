package com.cwoongc.study.jpa_fundmental.composite_key.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Identifying Relationship
 */
@Entity
@Getter
@Setter
public class ParentV3 {

    @Id @Column(name="parent_id")
    private String id;

    private String name;
}
