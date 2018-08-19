package com.cwoongc.study.jpa_fundmental.composite_key.entity.id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ChildV4Id implements Serializable {

    private String parentId; //@MapsId("parentId")로 매핑

    @Column(name="child_id")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildV4Id childV4Id = (ChildV4Id) o;
        return Objects.equals(parentId, childV4Id.parentId) &&
                Objects.equals(id, childV4Id.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(parentId, id);
    }
}
