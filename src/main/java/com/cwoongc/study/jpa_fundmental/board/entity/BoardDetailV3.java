package com.cwoongc.study.jpa_fundmental.board.entity;

import com.cwoongc.study.jpa_fundmental.board.entity.id.BoardDetailV3Id;

import javax.persistence.*;

/**
 * 잘못된 one to one identifying 예
 * @IdClass가 아니라 별도 @Id private Long boardId 속성을 만들고
 * @MapsId를 board에 부여해야한다.
 */
//@Entity
//@IdClass(BoardDetailV3Id.class)
public class BoardDetailV3 {

    @Id
    @OneToOne
    @JoinColumn(name = "board_id")
    private BoardV1 board;

    private String content;

}
