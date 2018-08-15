package com.cwoongc.study.jpa_fundmental.order.entity;

import com.cwoongc.study.jpa_fundmental.item.entity.ItemV2;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ORDER_ITEM")
@Getter
@Setter
public class OrderItemV2 {

    @Id
    @TableGenerator(name="order_item.order_item_id", pkColumnValue = "order_item.order_item_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_item.order_item_id")
    @Column(name = "ORDER_ITEM_ID", nullable = false)
    private Long id;

    @JoinColumn(name="ITEM_ID")
    @ManyToOne
    private ItemV2 item;

    @JoinColumn(name="ORDER_ID")
    @ManyToOne
    private OrderV2 order;

    private int orderPrice;

    private int count;

}
