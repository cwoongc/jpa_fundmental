package com.cwoongc.study.jpa_fundmental.item.entity;


import com.cwoongc.study.jpa_fundmental.common.jpa.entity.AuditAttributesInheritor;
import lombok.*;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) //
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Single-Table-Strategy
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //Table-per-Concret-Class-Strategy
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class ItemV4 extends AuditAttributesInheritor {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "item.item_id")
    @TableGenerator(name="item.item_id")
    private long id;

    private String name;

    private int price;

}


