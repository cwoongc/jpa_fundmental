package com.cwoongc.study.jpa_fundmental.item.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ITEM")
public class ItemV2 {

    @Id
    @TableGenerator(name="item.item_id", pkColumnValue = "item.item_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "item.item_id")
    @Column(name = "item_id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    private int stockQuantity;


}
