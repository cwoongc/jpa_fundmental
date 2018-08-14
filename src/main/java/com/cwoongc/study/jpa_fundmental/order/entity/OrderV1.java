package com.cwoongc.study.jpa_fundmental.order.entity;

import com.cwoongc.study.jpa_fundmental.order.type.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ORDERS")
@Data
public class OrderV1 {

    @Id @GeneratedValue
    @Column(name="ORDER_ID", nullable = false)
    private Long id;

    @Column(name="MEMBER_ID")
    private Long memberId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate; //주문 날짜

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;






}
