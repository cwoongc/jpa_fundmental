package com.cwoongc.study.jpa_fundmental.order.entity;

import com.cwoongc.study.jpa_fundmental.member.entity.MemberV2_;
import com.cwoongc.study.jpa_fundmental.order.type.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ORDERS")
@Getter
@Setter
public class OrderV2 {

    @Id
    @TableGenerator(name="orders.order_id", pkColumnValue = "orders.order_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "orders.order_id")
    @Column(name="ORDER_ID", nullable = false)
    private Long id;

    @JoinColumn(name="MEMBER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberV2_ member;

    @OneToMany(mappedBy = "order")
    private List<OrderItemV2> orderItems = new ArrayList<>();


    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate; //주문 날짜

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Access(AccessType.PROPERTY)
    public void setMember(MemberV2_ member) {
        if(this.member != null) this.member.getOrders().remove(this);

        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItemV2 orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }








}
