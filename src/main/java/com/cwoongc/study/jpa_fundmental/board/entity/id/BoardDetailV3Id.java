package com.cwoongc.study.jpa_fundmental.board.entity.id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class BoardDetailV3Id implements Serializable {

    private Long board;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDetailV3Id that = (BoardDetailV3Id) o;
        return Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {

        return Objects.hash(board);
    }
}
