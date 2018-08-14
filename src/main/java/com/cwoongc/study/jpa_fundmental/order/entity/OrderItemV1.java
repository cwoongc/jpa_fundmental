package com.cwoongc.study.jpa_fundmental.order.entity;

import javax.persistence.*;

@Entity
@Table(name="ORDER_ITEM")
public class OrderItemV1 {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID", nullable = false)
    private Long id;

    @Column(name="ITEM_ID")
    private Long itemId;

    private int orderPrice;

    private int count;

}
