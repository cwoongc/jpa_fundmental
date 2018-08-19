package com.cwoongc.study.jpa_fundmental.composite_key.entity;

import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.GrandChildV4Id;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GrandChildV4 {

    @EmbeddedId
    private GrandChildV4Id id;

    //EmbeddedId사용시 외래 키컬럼과 매핑한 FK 연관관계를 기본키에도 매핑시킬때 MapId사용
    /**
     * MapsId는 해당 엔티티에 @Id, @EmbeddedId 등이 설정된 속성을 확인하여, @Id일 경우(Primitive타입) @Id가 부여된 속성을 매핑하고
     * @EmbeddedId일 경우(@Embeddable객체) 해당 객체의 속성중에 @MapsId("childId")의 "childId"처럼  문자열인수로 주어진 이름의 속성을 찾아 매핑한다.
     * @MapsId 매핑의 의미는, 지정된 속성에 대하여 (@Id가 붙은 엔티티의 속성, 혹은 @EmbeddedId의 주어진 이름을 가진 속성)
     *  1. 관계 다중성 설정 (ex- @ManyToOne)
     *  2. JoinColumn 설정(관계의 주인 및 FK컬럼생성여부/FK컬럼명 지정)
     * 등의 설정을 @MapsId가 배치된 엔티티의 속성쪽에 설정하겠다는 의미이다.
     *
     */
    @MapsId("childId") //GrandChildV4Id.childId 매핑
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="parent_id"),
            @JoinColumn(name="child_id")
    })
    private ChildV4 child;

    private String name;
}
