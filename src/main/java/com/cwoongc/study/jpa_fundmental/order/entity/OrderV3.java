package com.cwoongc.study.jpa_fundmental.order.entity;

import com.cwoongc.study.jpa_fundmental.delivery.entity.DeliveryV3;
import com.cwoongc.study.jpa_fundmental.member.entity.MemberV3;
import com.cwoongc.study.jpa_fundmental.order.type.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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

    /**
     * ManyToOne의 경우 Many쪽(보고있는클래스) 엔티티에 One 필드를 set할때는 One객체의 Many 컬렉션에서 One 새 원소를 확인차 지우고,
     * 본 Many객체의 필드에 새 One 원소를 참조시키는 순서로 setter를 수정한다.
     * 추가로 새 One원소의 Many컬렉션에 본 Many 객체(this)를 추가한다.
     * @param member
     */
    @Access(AccessType.PROPERTY)
    public void setMember(MemberV3 member) {

        if(this.member != null) {
            this.member.getOrders().remove(member);
        }
        member.getOrders().add(this);

        this.member = member;
    }



    @OneToMany(mappedBy = "order")
    private List<OrderItemV3> orderItems = new ArrayList<>();

    /**
     * OneToMany는 주로 왜래키 주인이 아닌 엔티티인데
     * 해당 One 엔티티객체에서 Many 컬렉션 필드에 원소를 추가하는 행위를 대체하도록
     * add 편의 메소드를 만든다.
     *
     * Many 컬렉션 필드에 새 Many 객체를 추가하고,
     * 새 Many객체의 One필드에 본 One객체(this)를 set 한다.
     *
     * @param orderItem
     */
    public void addOrderItem(OrderItemV3 orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);

    }


    @OneToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryV3 delivery;


    @Access(AccessType.PROPERTY)
    public void setDelivery(DeliveryV3 delivery) {

        this.delivery = delivery;
        delivery.setOrder(this);
    }



    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
