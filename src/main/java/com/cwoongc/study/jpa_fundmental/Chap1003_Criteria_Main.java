package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.criteria.entity.CrMember;
import com.cwoongc.study.jpa_fundmental.criteria.type.JobType;
import org.hibernate.criterion.Distinct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class Chap1003_Criteria_Main {

    public static final String NAME_A = "hymoon";
    public static final int AGE_A = 35;
    public static final String NAME_B = "wcchoi";
    public static final int AGE_B = 39;
    public static final String NAME_C = "swchoi";
    public static final int AGE_C = 9;

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf, Chap1003_Criteria_Main::createCrMembers);
        TransactionConsumer.consume(emf, Chap1003_Criteria_Main::printCrMembers);
        TransactionConsumer.consume(emf, Chap1003_Criteria_Main::selectCrMembersWithWhereAndOrderbyClause);
        TransactionConsumer.consume(emf, Chap1003_Criteria_Main::selectCrMembersAgeGreaterThan10);
        TransactionConsumer.consume(emf, Chap1003_Criteria_Main::selectDistinctNameAndAge);


    }




    private static void createCrMembers(EntityManager em) {

        CrMember m1 = CrMember.builder()
                .name(NAME_A)
                .age(AGE_A)
                .job(JobType.Engineer)
                .build();

        em.persist(m1);

        CrMember m2 = CrMember.builder()
                .name(NAME_B)
                .age(AGE_B)
                .job(JobType.Engineer)
                .build();

        em.persist(m2);

        CrMember m3 = CrMember.builder()
                .name(NAME_C)
                .age(AGE_C)
                .job(JobType.Student)
                .build();

        em.persist(m3);

    }

    private static void printCrMembers(EntityManager em) {

        //크리테리아 빌더 생성
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //크리테리아 쿼리<엔티티> 생성 / 쿼리반환타입 지정 by 크리테리아 빌더
        CriteriaQuery<CrMember> cq = cb.createQuery(CrMember.class);

        //루트 생성 by 크리테리아 쿼리
        Root<CrMember> root = cq.from(CrMember.class);

        //SELECT 절 생성 : 크리테리아 쿼리 select 메소드를 호출하며 인자로 root를 넘김
        cq.select(root);

        //JPA 타입드 쿼리 생성 : 엔티티 매니져의 createQuery 메소드를 호출하며 인자로 CriteriaQuery를 넘김
        TypedQuery<CrMember> query = em.createQuery(cq);

        //JPA 타입드 쿼리로 결과 리스트 get
        List<CrMember> crMembers = query.getResultList();

        crMembers.stream()
                .forEach(cm->{
                    System.out.println(cm);
                });


    }

    private static void selectCrMembersWithWhereAndOrderbyClause(EntityManager em) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CrMember> cq = cb.createQuery(CrMember.class);

        //From (Root)
        Root<CrMember> r = cq.from(CrMember.class);

        //Where
        Predicate usernameEqual = cb.equal(r.get("job"), JobType.Engineer);

        //Order by
        Order ageDesc = cb.desc(r.get("age"));

        //Select
        cq.select(r)
                .where(usernameEqual)
                .orderBy(ageDesc);


        //만들어진 쿼리 실행은 Entity Manager 통해 함.
        TypedQuery<CrMember> tq = em.createQuery(cq);

        //Execute
        List<CrMember> crMembers = tq.getResultList();

        crMembers.stream()
                .forEach(crMember -> {
                    System.out.println(crMember);
                });

    }


    private static void selectCrMembersAgeGreaterThan10(EntityManager em) {

        /*
        select m from CrMember m
        where m.age > 10
        order by m.age desc, m.job asc
         */

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<CrMember> cq = cb.createQuery(CrMember.class);


        //From
        Root<CrMember> r = cq.from(CrMember.class);

        //Where
//        Predicate ageGt = cb.gt(r.get("age"),10);
        Predicate ageGt = cb.gt(r.<Integer>get("age"),10);

        //Order by
        Order ageDesc = cb.desc(r.get("age"));
        Order jobAsc = cb.asc(r.get("job"));

        //Select
        cq.select(r)
                .where(ageGt)
                .orderBy(ageDesc, jobAsc);

        //Execute
        TypedQuery<CrMember> tq = em.createQuery(cq);
        List<CrMember> crMembers = tq.getResultList();

        crMembers.stream()
                .forEach(m-> System.out.println(m));

    }


    private static void selectDistinctNameAndAge(EntityManager em) {
        /*
        select distinct m.name, m.age
        from CrMember m
         */

        //CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //CriteriaQuery
        CriteriaQuery<CrMember> cq = cb.createQuery(CrMember.class);


        //From
        Root<CrMember> r = cq.from(CrMember.class);

        //Select distinct
        cq.multiselect(r.get("name"), r.<Integer>get("age"))
                .distinct(true);
        //주) 컬럼지정 multiselect시에는 지정한 컬럼에 맵핑되는 속성으로 구성된 생성자가 엔티티 클래스에 요구된다.
        //위의 경우엔 name, age로 구성된 생성자가 CrMember에 존재해야함.

        //Execute
        TypedQuery<CrMember> tq = em.createQuery(cq);
        List<CrMember> crMembers = tq.getResultList();

        crMembers.stream()
                .forEach(m-> System.out.println(m));
    }


}
