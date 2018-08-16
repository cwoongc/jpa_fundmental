package com.cwoongc.study.jpa_fundmental.order.entity;

import com.cwoongc.study.jpa_fundmental.delivery.entity.DeliveryV3;
import com.cwoongc.study.jpa_fundmental.member.entity.MemberV3;
import com.cwoongc.study.jpa_fundmental.order.type.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ord")
@Getter
@Setter
public class OrderV3 {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ord.order_id")
    @TableGenerator(name="ord.order_id")
    @Column(name="order_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private MemberV3 member;

    @OneToMany(mappedBy = "order")
    private List<OrderItemV3> orderItems;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryV3 delivery;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
