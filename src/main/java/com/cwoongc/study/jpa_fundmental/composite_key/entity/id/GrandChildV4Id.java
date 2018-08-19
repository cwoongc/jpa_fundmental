package com.cwoongc.study.jpa_fundmental.composite_key.entity.id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class GrandChildV4Id implements Serializable {

    private ChildV4Id childId; //@MapsId("childId")로 매핑

    @Column(name="grandchild_id")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrandChildV4Id that = (GrandChildV4Id) o;
        return Objects.equals(childId, that.childId) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(childId, id);
    }
}
