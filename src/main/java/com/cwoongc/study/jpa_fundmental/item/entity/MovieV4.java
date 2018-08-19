package com.cwoongc.study.jpa_fundmental.item.entity;


import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MovieV4 extends ItemV4{

    private String director;

    private String actor;

    @Builder
    public MovieV4(Long creator, Long updator, String name, int price, String director, String actor) {
        super.setCreator(creator);
        super.setUpdater(updator);
        super.setName(name);
        super.setPrice(price);
        this.director = director;
        this.actor = actor;
    }
}
