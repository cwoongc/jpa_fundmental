package com.cwoongc.study.jpa_fundmental.composite_key.entity;

import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ParentV2Id;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ParentV2 {

    @EmbeddedId
    private ParentV2Id id;

    private String name;

}
