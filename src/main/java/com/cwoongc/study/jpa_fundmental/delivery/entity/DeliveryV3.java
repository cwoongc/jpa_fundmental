package com.cwoongc.study.jpa_fundmental.delivery.entity;

import com.cwoongc.study.jpa_fundmental.delivery.type.DeliveryStatus;
import com.cwoongc.study.jpa_fundmental.order.entity.OrderV3;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="delivery")
@Getter
@Setter
public class DeliveryV3 {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "delivery.delivery_id")
    @TableGenerator(name="delivery.delivery_id")
    @Column(name="delivery_id", nullable = false)
    private Long id;

    private String city;

    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private OrderV3 order;

}
