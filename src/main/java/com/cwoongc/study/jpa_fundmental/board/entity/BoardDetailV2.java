package com.cwoongc.study.jpa_fundmental.board.entity;

import javax.persistence.*;

@Entity
public class BoardDetailV2 {

    /*
    Aug 19, 2018 1:37:27 PM org.hibernate.mapping.RootClass checkCompositeIdentifier
    WARN: HHH000038: Composite-id class does not override equals(): com.cwoongc.study.jpa_fundmental.board.entity.BoardDetailV2
    Aug 19, 2018 1:37:27 PM org.hibernate.mapping.RootClass checkCompositeIdentifier
    WARN: HHH000039: Composite-id class does not override hashCode(): com.cwoongc.study.jpa_fundmental.board.entity.BoardDetailV2
     */
    @Id
    @OneToOne
    @JoinColumn(name = "board_id")
    private BoardV1 board;

    private String content;

}
