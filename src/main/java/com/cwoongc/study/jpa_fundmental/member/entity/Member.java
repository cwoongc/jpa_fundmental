package com.cwoongc.study.jpa_fundmental.member.entity;

import com.cwoongc.study.jpa_fundmental.member.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@ToString
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(
            name="ID", //컬럼명
            nullable = false //DDL시 not null 이 붙음
//            insertable = true, // false일 경우 엔티티 저장시 DB저장 대상에서 뺀다. readonly일경우 사용
//            updatable = true, // false일 경우 엔티티 업데이트시 DB업데이트 대상에서 뺀다. readonly일 경우 사용
//            unique = true, //[DDL용] 단일컬럼에 DB unique constraint생성시 사용. 복수 컬럼에 만들려면 @Table.uniqueConstraints 사용해야함
//            columnDefinition = "",  [DDL용] 컬럼의 default 값을 설정할때 사용.
//            length = 255, [DDL용] String 일경우에 한해 컬럼정의문에 문자길이를 넣어준다.
    )
    private String id;

    @Column(name="NAME")
    private String username;

    private Integer age;

    //추가
//    @Column(
//            //[DDL용] 정밀한 소수를 다뤄야 할경우 JAVA에서는 java.math.BigDecimal을 활용한다. MySql : decimal(10,2) 컬럼을 만들어줌.
//            precision = 10, //소수자릿수 포함한 전체자릿수
//            scale = 2 //소수자릿수
//    )
//    private BigDecimal salary;



    @Enumerated(EnumType.STRING)
    private RoleType roleType;


    //java.util.Date, java.util.Calendar를 맵핑할때 사용
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;


    //매핑하는 java의 타입에 따라 CLOB, BLOB이 자동 결정된다.
    @Lob
    private String description; //String의 경우 mysql은 longtext, byte[]이면 mysql은 longblob 이 매핑시킬수 있다.

//    //DB에 저장도, 조회도 하지 않는 객체 필드. 단순히 어플리케이션 쪽에서 임시캐싱공간으로 쓰는 대상 필드들에 사용.
//    @Transient
//    private String temp;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;




}
