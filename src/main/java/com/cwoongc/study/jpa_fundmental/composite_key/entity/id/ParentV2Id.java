package com.cwoongc.study.jpa_fundmental.composite_key.entity.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * JPA Embeddable
 * 1. Serializable 구현
 * 2. equals, hashCode 구현
 * 3. 기본생성자
 * 4. 클래스 public
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParentV2Id implements Serializable {

    @Column(name="parent_id1")
    private String id1;

    @Column(name="parent_id2")
    private String id2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentV2Id that = (ParentV2Id) o;
        return Objects.equals(id1, that.id1) &&
                Objects.equals(id2, that.id2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id1, id2);
    }
}
