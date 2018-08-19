package com.cwoongc.study.jpa_fundmental.member.entity;

import com.cwoongc.study.jpa_fundmental.common.jpa.entity.AuditAttributesInheritor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * MappedSuperclass 어노테이션이 붙은 클래스는
 * 테이블과 맵핑되지 앟고,
 * 자식 클래스(엔티티들)의 공통 속성을 한군대서 관리하기 위해 사용.
 *
 * 기본적으로 엔티티가 아니므로 영속대상이 아니고
 * 영속대상이 아니니 EntityManager를 통한 find()연산, JPQL에서 사용될수 없다.
 *
 * InheritanceType.TABLE_PER_CLASS, SINGLE_TABLE와 차이점은
 * 상속전략을 써서 상속유형을 바꿔가며 DB 테이블의 Super Sub type 구조를 바꾸고자 함이 아니라,
 *
 * 어플리케이션 쪽(도메인객체쪽)에서 DB 컬럼과 맵핑시킬 공통속성을 수퍼클래스 한군대서 관리하고,
 * 서브클래스에서는 각기 확장된 속성들만 추가정의하여
 * SINGLE 테이블에 대한 다양한 VIEW로 Query하기 위함이 더크다고 생각된다.
 * 이경우 서브클래스들은 모두 같은 table과 맵핑되야 한다.
 *
 *
 */
@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class PartyV5 extends AuditAttributesInheritor {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "party.party_id")
    @TableGenerator(name="party.party_id", pkColumnValue = "party.party_id", allocationSize = 1)
    private Long id;

    private String name;
}
