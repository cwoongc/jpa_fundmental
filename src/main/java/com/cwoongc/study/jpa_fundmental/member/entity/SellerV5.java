package com.cwoongc.study.jpa_fundmental.member.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "party")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AttributeOverrides({
//        @AttributeOverride(name = "id", column = @Column(name="seller_id")),
        @AttributeOverride(name = "name", column = @Column(name="seller_name"))
})
public class SellerV5 extends PartyV5 {

    private String shopName;

    @Builder
    public SellerV5(Long creator, String name, String shopName) {
        super.setCreator(creator);
        super.setUpdater(creator);
        super.setName(name);
        this.shopName = shopName;
    }
}
