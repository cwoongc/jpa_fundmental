package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.jpql.dto.JpUserDTO;
import com.cwoongc.study.jpa_fundmental.jpql.entity.JpMember;
import com.cwoongc.study.jpa_fundmental.jpql.entity.JpTeam;
import com.cwoongc.study.jpa_fundmental.jpql.entity.value.Address;

import javax.persistence.*;
import java.util.List;

public class Chap1002_JPQL_Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");


        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::createDefaultData);

//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useJPQLTypeQuery);
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useJPQLQuery);
//
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useJPQLNamedParameter);
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useJPQLPositionalParameter);
//
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useEntityProjection);
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useEmbeddedTypeProjection);
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useScalaTypeProjection);
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useMultiScalaValueProjection);
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useMultiEntityEmbeddedValueProjection);
//
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useNew);
//
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::usePagingAPI);
//
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useJPQLInnerJoin);
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useJPQLLeftJoin);
//        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useJPQLCollectionJoin);

        TransactionConsumer.consume(emf, Chap1002_JPQL_Main::useJPQLFetchJoin);


    }

    /**
     * 패치 조인은 레이지 로딩을 하지않고 inner join SQL을 만들어 한번에 데이터를 가져오면서
     * 조인 대상 2객체간 객체 그래프도 정상적으로 가져오는 JPQL 조인을 지칭한다.
     *
     * join fetch 구문으로 패치 조인을 사용할 수 잇다.
     * join fetch 시에 뒤에 쓰는 연관필에는 alias사용이 불가능하다.
     * @param entityManager
     */
    private static void useJPQLFetchJoin(EntityManager entityManager) {

        List<JpMember> jpMembers = entityManager.createQuery("select m from JpMember m join fetch m.team", JpMember.class)
                .getResultList();

        for(JpMember m : jpMembers) {
            System.out.println("username: "+m.getName()+", teamname: "+m.getTeam().getName());
        }

    }

    private static void useJPQLCollectionJoin(EntityManager entityManager) {

        List<Object[]> teamAndMembers = entityManager.createQuery("select t, m from JpTeam t left join t.members m")
                .getResultList();

        teamAndMembers.stream()
                .forEach(tam -> {
                    JpTeam t = (JpTeam)tam[0];
                    JpMember m = (JpMember)tam[1];

                    System.out.println(t);
                    System.out.println(m);
                });
    }

    private static void useJPQLLeftJoin(EntityManager entityManager) {

        entityManager.createQuery("select m from JpMember m left join m.team t",JpMember.class)
                .getResultList()
                .stream()
                .forEach(m-> System.out.println(m));
    }

    /**
     * JPQL join의 가장큰 차이점은 driving entity가 가진 '연관필드'를 이용하여 조인한다는 점.
     * 연관필드 없이는 조인이 불가능하다.
     *
     * error ) select m from member m join team t where m.id = t.id
     * ok ) select m from member m join m.team t
     * @param entityManager
     */
    private static void useJPQLInnerJoin(EntityManager entityManager) {

        TypedQuery<JpMember> tq = entityManager.createQuery("select m from JpMember m inner join m.team t where t.name = :teamName", JpMember.class);

        tq.setParameter("teamName", "Finance");

        List<JpMember> jpMembers = tq.getResultList();

        jpMembers.stream()
                .forEach(m-> System.out.println(m));


        Query q = entityManager.createQuery("select m, t from JpMember m join m.team t");

        List<Object[]> memberAndTeamList = q.getResultList();

        memberAndTeamList.stream()
                .forEach(mnt->{
                    JpMember m = (JpMember)mnt[0];
                    JpTeam t = (JpTeam)mnt[1];
                    System.out.println(m);
                    System.out.println(t);
                });




    }

    private static void usePagingAPI(EntityManager entityManager) {

        TypedQuery<JpMember> tq = entityManager.createQuery("select m from JpMember m order by m.name desc", JpMember.class);

        tq.setFirstResult(1); //시작 offset. 0이 시작.
        tq.setMaxResults(2); //가져올 row 수

        tq.getResultList()
                .stream()
                .forEach(m-> System.out.println(m));


    }

    /**
     * new는 jpql select 구문에서 객체 매핑을 지원.
     * new 에 사용되는 객체는
     * 1. full pacakge 명 필요
     * 2. 순서가 동일한 생성자 필요
     * @param entityManager
     */
    private static void useNew(EntityManager entityManager) {

        List<JpUserDTO> jpUserDTOList = entityManager.createQuery("select new com.cwoongc.study.jpa_fundmental.jpql.dto.JpUserDTO(m.name, m.age) from JpMember m",JpUserDTO.class)
                .getResultList();

        jpUserDTOList.stream()
                .forEach(jpUserDTO -> System.out.println(jpUserDTO));





    }

    private static void useMultiEntityEmbeddedValueProjection(EntityManager entityManager) {

        List<Object[]> resultList = entityManager.createQuery("select m.team, m.address, m.name from JpMember m")
                .getResultList();

        resultList.stream()
                .forEach(cols->{
                    JpTeam t = (JpTeam)cols[0];
                    Address a = (Address)cols[1];
                    String n = (String)cols[2];

                    System.out.println(t);
                    System.out.println(a);
                    System.out.println(n);
                });



    }

    private static void useMultiScalaValueProjection(EntityManager entityManager) {
        Query q = entityManager.createQuery("select m.name, m.age from JpMember m");

        List results = q.getResultList();

        results.stream()
                .forEach(r->{
                    Object[] cols = (Object[])r;
                    System.out.println("name: "+cols[0]);
                    System.out.println("age: "+cols[1]);
                });

        List<Object[]> rows = q.getResultList();

        rows.stream()
                .forEach(r->{
                    System.out.println(r[0]);
                    System.out.println(r[1]);
                });




    }

    private static void useScalaTypeProjection(EntityManager entityManager) {

        List<String> names = entityManager.createQuery("select m.name from JpMember m")
                .getResultList();

        names.stream()
                .forEach(n-> System.out.println(n));


        entityManager.createQuery("select distinct m.team.name from JpMember m")
                .getResultList()
                .stream()
                .forEach(tn-> System.out.println(tn));
    }

    /**
     * embedded type projection
     *
     * embbeded type의 projection은 반드시 entity를 거쳐 진행해야 한다.
     * @param entityManager
     */
    private static void useEmbeddedTypeProjection(EntityManager entityManager) {

        TypedQuery<Address> tq = entityManager.createQuery("select m.address from JpMember m", Address.class);

        tq.getResultList().stream()
                .forEach(a-> System.out.println(a));


    }

    /**
     * entity projection
     * @param entityManager
     */
    private static void useEntityProjection(EntityManager entityManager) {

        TypedQuery<JpMember> tq1 = entityManager.createQuery("select m from JpMember m", JpMember.class);

        tq1.getResultList().stream()
                .forEach(m-> System.out.println(m));

        TypedQuery<JpTeam> tq2 = entityManager.createQuery("select m.team from JpMember m", JpTeam.class);

        tq2.getResultList().stream()
                .forEach(t-> System.out.println(t));

    }

    private static void useJPQLPositionalParameter(EntityManager entityManager) {

        TypedQuery<JpMember> tq = entityManager.createQuery("select m from JpMember m where m.name = ?1",JpMember.class);

        List<JpMember> jpMembers = tq.setParameter(1, "swchoi")
                .getResultList();

        jpMembers.stream()
                .forEach(m-> System.out.println(m));

    }

    private static void useJPQLNamedParameter(EntityManager entityManager) {

        TypedQuery<JpMember> tq = entityManager.createQuery("select m from JpMember m where m.name = :name", JpMember.class);

        List<JpMember> jpMembers = tq.setParameter("name", "wcchoi")
                .getResultList();

        jpMembers.stream()
                .forEach(m-> System.out.println(m));
    }

    private static void useJPQLQuery(EntityManager entityManager) {

        //조회 대상 컬럼이 2개 이상이면 추후 쿼리반환 결과는 Object[]가 된다.
        Query q = entityManager.createQuery("select m.name, m.age from JpMember m");

        List<Object> rows = q.getResultList();

        rows.stream()
                .forEach(row->{
                    Object[] cols = (Object[])row;
                    System.out.println("name: "+cols[0]);
                    System.out.println("age: "+cols[1]);
                });
    }

    private static void useJPQLTypeQuery(EntityManager entityManager) {

        TypedQuery<JpMember> tq = entityManager.createQuery("SELECT m FROM JpMember m", JpMember.class);

        List<JpMember> jpMembers = tq.getResultList();

        jpMembers.stream()
                .forEach(m-> System.out.println(m));

    }

    private static void createDefaultData(EntityManager entityManager) {

        JpMember m1 = JpMember.builder()
                .name("wcchoi")
                .age(39)
                .build();

        entityManager.persist(m1);

        JpMember m2 = JpMember.builder()
                .name("hymoon")
                .age(35)
                .build();

        entityManager.persist(m2);

        JpMember m3 = JpMember.builder()
                .name("swchoi")
                .age(9)
                .build();

        entityManager.persist(m3);

        JpTeam t1 = JpTeam.builder()
                .name("Data Engineering")
                .build();

        entityManager.persist(t1);

        JpTeam t2 = JpTeam.builder()
                .name("Finance")
                .build();

        entityManager.persist(t2);

        m1.setTeam(t1);

        m2.setTeam(t2);

        m3.setTeam(t2);


        Address a1 = Address.builder()
                .city("seoul")
                .steet("30-7")
                .state("korea")
                .build();

        m1.setAddress(a1);
        m2.setAddress(a1);
        m3.setAddress(a1);


    }
}
