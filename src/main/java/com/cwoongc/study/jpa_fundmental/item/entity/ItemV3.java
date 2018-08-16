package com.cwoongc.study.jpa_fundmental.item.entity;

import com.cwoongc.study.jpa_fundmental.category.entity.CategoryV3;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="item")
@Getter
@Setter
public class ItemV3 {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "item.item_id")
    @TableGenerator(name="item.item_id")
    @Column(name="item_id", nullable = false)
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<CategoryV3> categories = new ArrayList<>();



}
