package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.embedded_type.entity.MemberV2;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.PhoneNumber;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.Zipcode;
import com.cwoongc.study.jpa_fundmental.embedded_type.repository.MemberV2Repository;
import com.cwoongc.study.jpa_fundmental.jpql.entity.JpMember;
import com.cwoongc.study.jpa_fundmental.jpql.entity.JpTeam;
import com.cwoongc.study.jpa_fundmental.jpql.entity.value.Address;
import com.cwoongc.study.jpa_fundmental.jpql.repository.JpMemberRepository;
import com.cwoongc.study.jpa_fundmental.jpql.repository.JpTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import javax.persistence.spi.PersistenceProvider;

@EnableJpaRepositories(basePackageClasses = JpaFundmentalApplication.class)
@SpringBootApplication
public class JpaFundmentalApplication {

    @Autowired
    private MemberV2Repository memberV2Repository;

    @Autowired
    private JpMemberRepository jpMemberRepository;

    @Autowired
    private JpTeamRepository jpTeamRepository;



    public static void main(String[] args) {
        SpringApplication.run(JpaFundmentalApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(ApplicationContext ctx) {
        return args -> {



            JpMember m1 = JpMember.builder()
                    .name("wcchoi")
                    .age(39)
                    .build();

            jpMemberRepository.save(m1);

            JpMember m2 = JpMember.builder()
                    .name("hymoon")
                    .age(35)
                    .build();

            jpMemberRepository.save(m2);

            JpMember m3 = JpMember.builder()
                    .name("swchoi")
                    .age(9)
                    .build();

            jpMemberRepository.save(m3);

            JpTeam t1 = JpTeam.builder()
                    .name("Data Engineering")
                    .build();

            jpTeamRepository.save(t1);

            JpTeam t2 = JpTeam.builder()
                    .name("Finance")
                    .build();

            jpTeamRepository.save(t2);

            jpTeamRepository.flush();


            m1.setTeam(t1);

            m2.setTeam(t2);

            m3.setTeam(t2);


            com.cwoongc.study.jpa_fundmental.jpql.entity.value.Address a1 = Address.builder()
                    .city("seoul")
                    .steet("30-7")
                    .state("korea")
                    .build();

            m1.setAddress(a1);
            m2.setAddress(a1);
            m3.setAddress(a1);

            jpMemberRepository.save(m1);
            jpMemberRepository.save(m2);
            jpMemberRepository.save(m3);


            jpMemberRepository.flush();




            PageRequest pageRequest = PageRequest.of(1,2, new Sort(Sort.Direction.DESC,"name"));


            Page<JpMember> jpMemberPage = jpMemberRepository.findAll(pageRequest);

            List<JpMember> contents = jpMemberPage.getContent();
            int totalPage = jpMemberPage.getTotalPages();
            boolean hasNextPage = jpMemberPage.hasNext();

            contents.stream()
                    .forEachOrdered(m->System.out.println(m));

            System.out.println(totalPage);
            System.out.println(hasNextPage);



            /**
              * Pageable, PageRequest, PageRequestOf, Sort, Page<T>
              */
//            PageRequest pageRequest = PageRequest.of(0,10, new Sort(Sort.Direction.DESC,"name"));
//
//            Page<MemberV2> memberV2Page = memberV2Repository.findByNameStartingWith("hy", pageRequest);
//
//            List<MemberV2> contents = memberV2Page.getContent(); //조회된 데이터
//            int totalPage = memberV2Page.getTotalPages(); //전체 페이지수
//            boolean hasNextPage = memberV2Page.hasNext(); //다음페이지 존재여부
//
//            contents.stream()
//                    .forEachOrdered(m-> System.out.println(m.getName()));
//
//            System.out.println(totalPage);
//            System.out.println(hasNextPage);


        };
    }




//    @Bean
//    public CommandLineRunner run(ApplicationContext ctx) {
//        return args -> {
//
//            MemberV2 member1 = MemberV2.builder()
//                    .name("wcchoi")
//                    .homeAddress(Address.builder()
//                            .city("seoul")
//                            .state("Korea")
//                            .street("yeong-dong-dae-ro")
//                            .zipcode(Zipcode.builder()
//                                    .zip("06080")
//                                    .plusFour("?")
//                                    .build()
//                            )
//                            .build()
//                    ).companyAddress(Address.builder()
//                            .city("seoul")
//                            .state("Korea")
//                            .street("seoul-ro")
//                            .zipcode(Zipcode.builder()
//                                    .zip("06084")
//                                    .plusFour("?")
//                                    .build()
//                            )
//                            .build()
//                    ).phoneNumber(PhoneNumber.builder()
//                            .areaCode("82")
//                            .localNumber("1090487331")
//                            .build()
//                    )
//                    .build();
//
//            memberV2Repository.save(member1);
//
//            MemberV2 member2 = MemberV2.builder()
//                    .name("hymoon")
//                    .homeAddress(Address.builder()
//                            .city("seoul")
//                            .state("Korea")
//                            .street("yeong-dong-dae-ro")
//                            .zipcode(Zipcode.builder()
//                                    .zip("06080")
//                                    .plusFour("?")
//                                    .build()
//                            )
//                            .build()
//                    ).companyAddress(Address.builder()
//                            .city("seoul")
//                            .state("Korea")
//                            .street("seoul-ro")
//                            .zipcode(Zipcode.builder()
//                                    .zip("06084")
//                                    .plusFour("?")
//                                    .build()
//                            )
//                            .build()
//                    ).phoneNumber(PhoneNumber.builder()
//                            .areaCode("82")
//                            .localNumber("1092553405")
//                            .build()
//                    )
//                    .build();
//
//            memberV2Repository.save(member2);
//
//            memberV2Repository.findByNameAndHomeAddress_City("hymoon","seoul")
//                    .stream()
//                    .forEach(m->{
//                        System.out.println(m.getPhoneNumber().getLocalNumber());
//                    });
//
//
//            /**
//             * Pageable, PageRequest, PageRequestOf, Sort, Page<T>
//             */
//            PageRequest pageRequest = PageRequest.of(0,10, new Sort(Sort.Direction.DESC,"name"));
//
//            Page<MemberV2> memberV2Page = memberV2Repository.findByNameStartingWith("hy", pageRequest);
//
//            List<MemberV2> contents = memberV2Page.getContent(); //조회된 데이터
//            int totalPage = memberV2Page.getTotalPages(); //전체 페이지수
//            boolean hasNextPage = memberV2Page.hasNext(); //다음페이지 존재여부
//
//            contents.stream()
//                    .forEachOrdered(m-> System.out.println(m.getName()));
//
//            System.out.println(totalPage);
//            System.out.println(hasNextPage);
//
//
//
//        };
//    }

//    public static void main(String[] args) {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit"); //Session Factory
//
//        EntityManager em = emf.createEntityManager(); //Session, Do Not Share EntityManager!!
//
//        EntityTransaction tx = em.getTransaction();
//
//        try {
//            tx.begin();
//            logic(em);
//            logic_m_1(em);
//            login_2_Fetch_Type(em);
//            tx.commit(); //flush
//            em.close();
//
//
//            em = emf.createEntityManager();
//            tx = em.getTransaction();
//            tx.begin();
//            login_eager(em);
//            tx.commit();
//            em.close();
//
//            em = emf.createEntityManager();
//            tx = em.getTransaction();
//            tx.begin();
//            login_lazy(em);
//            tx.commit();
//
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        emf.close();
//    }



//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties springDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource")
//    public DataSource springDataSource() {
//        return springDataSourceProperties().initializeDataSourceBuilder().build();
//    }


//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactoryBean(org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder builder) {
//
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = builder
//                .dataSource(springDataSource())
//                .packages(JpaFundmentalApplication.class)
//                .persistenceUnit("pu-members")
//                .build();
//
//
//        entityManagerFactoryBean.setPersistenceProvider(new org.hibernate.jpa.HibernatePersistenceProvider());
//        entityManagerFactoryBean.setPersistenceUnitName("pu-members");
//
//        return entityManagerFactoryBean;
//    }

//    @Bean
//    public CommandLineRunner jpaMain(ApplicationContext ctx) {
//        return args -> {
//
//
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit"); //Session Factory
//
//            EntityManager em = emf.createEntityManager(); //Session, Do Not Share EntityManager!!
//
//            EntityTransaction tx = em.getTransaction();
//
//            try {
//                tx.begin();
//                logic(em);
//                tx.commit();
//
//            } catch (Exception e) {
//                tx.rollback();
//            } finally {
//                em.close();
//            }
//            emf.close();
//
//        };
//    }


    /*

    public static void logic(EntityManager em) {

        String id = "id1";
        Member member = new Member(); //member : new
        member.setId(id); //member: transient
        member.setUsername("웅철"); //member: transient
        member.setAge(2); //member: transient

        //등록
        em.persist(member); //member: managed, twb-insert1

        //수정
        member.setAge(20); //member: managed, dirtied

        //한건 조회 (pk조회), DB에 가지 않는다.
        Member findMember = em.find(Member.class, id); //findMember == member: managed, dirtied

        //조회멤버 출력
        System.out.println("findMember="+ findMember.getUsername()+ ", age=" + findMember.getAge());

        //목록조회
        // 이 시점에 등록(insert), 수정(update), 목록조회 (select) 가 동시에 순서대로 DB로 날라가고 (목록조회시엔 현재 모든 Member목록을 보려면 영속성 컨텍스트 상에선 알수가 없고 DB에 가야되기에 계류중인 SQL을 모두 실행)
        List<Member> members =
                em.createQuery("select m from MemberV1 m", Member.class).getResultList(); //flush->dirty check, twb-update1 -> DB insert, update, select (FlushModeType.AUTO)


        System.out.println("members.size="+members.size());

        //삭제 //commit시점에 날라간다.
        em.remove(member); // member: removed, twb-delete



    }




    public static void logic_m_1(EntityManager em) {

        Team t1 = new Team(); //t1:new

        t1.setId("DI");
        t1.setName("Data Infrastructure");
        t1.setDescription(t1.getId()+":"+t1.getName());

        em.persist(t1);//t1:managed, twb-insert1
        System.out.println(t1);

        Member m1 = new Member(); //m1:new
        String m1Id =  "cwoongc";
        m1.setId(m1Id);
        m1.setAge(39);
        m1.setUsername("choi woong chul");

        em.persist(m1); //m1:managed, twb-insert2
        System.out.println(m1);


        em.flush(); //flush, insert1, insert2

        MemberM_1 m2 = em.find(MemberM_1.class, m1Id); //m2:managed, select
        System.out.println(m2);

        m2.setTeam(t1); //m2:managed, dirtied

//        em.detach(t1); // t1:managed->detached 되면 DB에서 Team을 쿼리해온다. 안하면 그냥 영속 컨텍스트의 t1을 사용(쿼리안함)


        //m3:managed, flush, dirty check, twb_update1, update1, select
        MemberM_1 m3 = em.createQuery("select m from MemberM_1 m where m.id=:id", MemberM_1.class)
                .setParameter("id",m1Id)
                .getSingleResult();

        System.out.println(m3);



    }

    public static void login_2_Fetch_Type(EntityManager em) {

        Team t1 = new Team(); //t1:new

        t1.setId("DI");
        t1.setName("Data Infrastructure");
        t1.setDescription(t1.getId()+":"+t1.getName());

        em.persist(t1);
        System.out.println(t1);

        String m1id = "m1";//, m2id = "m2";

        Member m1 = new Member(m1id,"woong-chul",39,RoleType.USER,new Date(),new Date(), "m1 abcdefghi.....", t1);

        em.persist(m1);
        System.out.println(m1);

//        em.flush();
//        em.detach(m1);
//        em.detach(t1);

//        m1 = em.createQuery("select m from Member m where m.id=:id",Member.class)
//                .setParameter("id",m1id)
//                .getSingleResult();
//        Member m1_1 = em.getReference(Member.class, m1id);
//
//        System.out.println(m1_1);
//
//        String teamName = m1_1.getTeam().getName();
//        System.out.println(teamName);




    }

    public static void login_eager(EntityManager em) {

        String m1id = "m1";
        Member m1 = em.getReference(Member.class, m1id);
        String m1id_got = m1.getId();
        String m1name_got = m1.getUsername();

        Team t1 = m1.getTeam();
        System.out.println(t1);

    }

    public static void login_lazy(EntityManager em) {

        String m1id = "m1";
        MemberM_1 m1 = em.getReference(MemberM_1.class, m1id);
        String m1id_got = m1.getId();
        String m1name_got = m1.getName();

        Team t1 = m1.getTeam();
        System.out.println(t1);

    }

    */


}
