package com.cwoongc.study.jpa_fundmental.category.entity;

import com.cwoongc.study.jpa_fundmental.item.entity.ItemV3;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
@Getter
@Setter
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
    private List<CategoryV3> children = new ArrayList<>();

    public void addChildCategory(CategoryV3 child) {
        this.children.add(child);
        child.setParent(this);
    }



    private String name;

    @ManyToMany
    @JoinTable(name="category_item", joinColumns = @JoinColumn(name="category_id"), inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<ItemV3> items = new ArrayList<>();


    public void addItem(ItemV3 item) {
        this.items.add(item);
        item.getCategories().add(this);
    }
}
