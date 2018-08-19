package com.cwoongc.study.jpa_fundmental.board.entity;

import javax.persistence.*;

@Entity
public class BoardV2 {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "board.board_id")
    @TableGenerator(name = "board.board_id", pkColumnValue = "board.board_id")
    @Column(name = "board_id", nullable = false)
    private Long id;

    private String title;

    @OneToOne(mappedBy = "board")
    private BoardDetailV1 boardDetail;

}
