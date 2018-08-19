package com.cwoongc.study.jpa_fundmental.composite_key.entity.id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class ChildV3Id implements Serializable {


    private String parent;
    private String childId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildV3Id childV3Id = (ChildV3Id) o;
        return Objects.equals(parent, childV3Id.parent) &&
                Objects.equals(childId, childV3Id.childId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(parent, childId);
    }
}
