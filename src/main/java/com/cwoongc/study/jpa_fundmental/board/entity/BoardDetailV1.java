package com.cwoongc.study.jpa_fundmental.board.entity;

import javax.persistence.*;

/**
 * 1:1 Identifying 제대로된 사용예
 *
 * 반드시 @Id를 붙인 부모의 키와 똑같은 속성을 만들고,
 * @MapsId를 붙이는 부모객체타입의 속성을 만들고,
 * @OneToOne & @JoinColumn 지정하여 관계의 주인과 FK컬럼생성여부를 설정한다.
 *
 */
@Entity
public class BoardDetailV1 {

    @Id
    private Long boardId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "board_id")
    private BoardV1 board;

    private String content;

}
