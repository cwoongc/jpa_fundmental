package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.jpql.entity.JpMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Chap03_Persistence_Context_Management_Main {


    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf,Chap03_Persistence_Context_Management_Main::learnEntity4States);
    }

    /**
     * 학습대상
     *
     * 1. 엔티티의 4가지 상태
     * 2. 엔티티 상태를 전이시키는 엔티티 매니져의 메소드
     * 3. 엔티티 상태에 따른 영속성 컨텍스트의 상태
     * 4. 엔티티 상태를 전이시 영속성 컨텍스트에게 일어나는 상호작용
     * @param entityManager
     */
    private static void learnEntity4States(EntityManager entityManager) {

        //엔티티의 4가지 상태 : 신규(new/transient), 관리되는(managed), 분리된(detached), 제거된(removed)

        //1. 신규(new/transient) 상태
        // -- 영속성 컨텍스트나 DB가 새로 생성된 엔티티 객체의 존재를 모르는 시점
        JpMember member = new JpMember();
        member.setName("wcchoi");

        //2. 관리되는(managed) 상태
        // -- 엔티티 객체가 영속성 컨텍스트에 의해 관리되는 상태.
        // -- 엔티티 매니져의 persist(Object entity) 메소드를 명시적으로 호출하여 new --> managed 상태전이 시킬 수 있음
        entityManager.persist(member); // new --> managed
        entityManager.flush(); // 트랜잭션 안에서 커밋전이나 flush를 명시적으로 호출하면 flush가 발생한다. DB Insert

        //3. 분리된(detached) 상태
        // -- 엔티티 객체가 영속성 컨텍스트로 부터 분리되어 더이상 관리되지 않는 상태
        // -- 관리되던 엔티티 객체만이 분리 상태가 될 수 있다. (detacted로의 상태전이는 managed 상태에서만 가능)
        // -- 엔티티 매니져의 detached(Object entity) 메소드를 명시적으로 호출하여 managed --> detached 상태전이 시킬 수 있다.
        // -- 엔티티 매니져의 clear() : "영속성 컨텍스트 초기화. 관리하던 엔티티 객체를 모두 분리",
        //                stop(): "영속성 컨텍스트 닫기. 관리하던 엔티티 객체를 모두 분리하고 영속성 컨텍스트의 동작을 정지시킴."
        //    을 통해서도 관리되던(managed) 엔티티 객체를 분리된(detached) 상태로 전이 시킬 수 있다.
        // -- 분리된(detacted) 상태의 엔티티 객체를 다시 영속성 컨텍스트에 의해 관리되는(manged) 엔티티 객체로 상태를 전이하고 싶으면,
        //    엔티티 매니져의 merge(T entity) 메소드를 명시적으로 호출해야 한다. (detached --> managed)
        entityManager.detach(member); //managed --> detached
//        entityManager.clear();
        entityManager.merge(member); //find & merge (detached --> managed)


        //4. 제거된(removed) 상태
        // -- 엔티티 객체가 영속성 컨텍스트로 부터 제거되어 더이상 관리되지 않으며, DB로 부터도 삭제(DELETE) 될/된 상태.
        // -- 엔티티 매니져의 remove(Object entity) 메소드를 명시적으로 호출하여 managed --> removed 상태 전이 시킬 수 있다.
        // -- 엔티티 매니져가 flush를 통해 영속성 컨텍스트의 엔티티 객체들의 상태를 DB로 내려보낼 때, DB에서 삭제대상 row가 되는 엔티티 객체의 상태이다.
        // -- 제거된(removed) 상태의 엔티티 객체를 다시 영속성 컨텍스트에 관리되는(managed) 상태로 전이시키고 싶으면,
        //    엔티티 매니져의 persist(Object entity) 메서드를 명시적으로 호출하면 된다. (removed --> managed)
        entityManager.persist(member); // new --> managed
        entityManager.remove(member); // manged --> removed
        entityManager.flush(); // 엔티티 객체 상태를 DB로 내려보냄 : 여기서는 DB ROW DELETE

        //주) JPA 트랜잭션의 commit()은 트랜잭션 시작 부터 커밋시 까지 발생한 엔티티 객체들의 상태변화를 트랜잭션 도중에 DB에 내려보냄(flush) 없이
        //    커밋시에 한번의 flush로 원자적으로 반영되도록 하는 것이다.
        //    즉, commit은 flush를 막았다가 트리커링 하는 트랜잭션의 행위이고, DB에 엔티티 객체들의 상태를 적용시키는 것은 flush 이다.




    }


}
