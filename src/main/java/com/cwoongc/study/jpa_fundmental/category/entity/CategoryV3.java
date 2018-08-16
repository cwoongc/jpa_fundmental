package com.cwoongc.study.jpa_fundmental.category.entity;

import com.cwoongc.study.jpa_fundmental.item.entity.ItemV3;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Category")
public class CategoryV3 {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "category.category_id")
    @TableGenerator(name="category.category_id")
    @Column(name="category_id")
    private Long id;

    @JoinColumn(name="parent_id")
    @ManyToOne
    private CategoryV3 parent;

    @OneToMany(mappedBy = "parent")
    private List<CategoryV3> children;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item", joinColumns = @JoinColumn(name="category_id"), inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<ItemV3> items;


    public void addItem(ItemV3 item) {
        this.items.add(item);
        item.getCategories().add(this);
    }
}
