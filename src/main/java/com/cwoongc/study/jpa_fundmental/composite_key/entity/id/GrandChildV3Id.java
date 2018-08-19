package com.cwoongc.study.jpa_fundmental.composite_key.entity.id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class GrandChildV3Id implements Serializable {

    //상속인 클래스가 IdClass 사용시(부모도 복합키 클래스일경우), 상속인 클래스가 아니라 상속인의 IdClass를 상속자 IdClass에 기술
    private ChildV3Id child;
    private String id;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrandChildV3Id that = (GrandChildV3Id) o;
        return Objects.equals(child, that.child) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(child, id);
    }
}
