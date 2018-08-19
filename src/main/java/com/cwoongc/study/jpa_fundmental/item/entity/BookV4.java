package com.cwoongc.study.jpa_fundmental.item.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "book_id")
@Getter
@Setter
@NoArgsConstructor
// 본 엔티티에 연결되는 테이블에 부모테이블의 pk를 식별자 관계로 상속받는 본테이블의 pk컬럼명을 PrimaryJoinKeyColumn 어노테이션으로 제정의했다.
public class BookV4 extends ItemV4 {

    private String author;

    private String isbn;

    @Builder
    public BookV4(Long creator, Long updator, String name, int price, String author, String isbn) {
        super.setCreator(creator);
        super.setUpdater(updator);
        super.setName(name);
        super.setPrice(price);
        this.author = author;
        this.isbn = isbn;
    }
}
