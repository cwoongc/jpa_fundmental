package com.cwoongc.study.jpa_fundmental.composite_key.entity.id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


/**
 * JPA IdClass
 * 1. Serializable 구현
 * 2. equals, hashCode 구현
 * 3. 기본생성자
 * 4. 클래스 public
 */
@NoArgsConstructor
@AllArgsConstructor
public class ParentV1Id implements Serializable {

    private String id1;
    private String id2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentV1Id that = (ParentV1Id) o;
        return Objects.equals(id1, that.id1) &&
                Objects.equals(id2, that.id2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id1, id2);
    }
}
