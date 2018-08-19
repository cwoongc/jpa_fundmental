package com.cwoongc.study.jpa_fundmental.item.entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter
@Setter
@NoArgsConstructor
public class AlbumV4 extends ItemV4{

    private String artist;

    @Builder
    public AlbumV4(Long creator, Long updator, String name, int price, String artist) {
        super.setCreator(creator);
        super.setUpdater(updator);
        super.setName(name);
        super.setPrice(price);
        this.artist = artist;
    }



}
