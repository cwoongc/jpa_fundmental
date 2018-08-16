package com.cwoongc.study.jpa_fundmental.order.entity;

import com.cwoongc.study.jpa_fundmental.item.entity.ItemV3;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter
@Setter
public class OrderItemV3 {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_item.order_item_id")
    @TableGenerator(name="order_item.order_item_id")
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private ItemV3 item;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderV3 order;

    private int orderPrice;

    private int count;

}
