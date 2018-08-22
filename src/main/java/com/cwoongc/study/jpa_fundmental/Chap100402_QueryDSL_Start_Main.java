package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.MemberV2;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.QMemberV2;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.Address;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.PhoneNumber;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.Zipcode;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static com.cwoongc.study.jpa_fundmental.embedded_type.entity.QMemberV2.memberV2;

public class Chap100402_QueryDSL_Start_Main {
    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf, Chap100402_QueryDSL_Start_Main::createData);
        TransactionConsumer.consume(emf, Chap100402_QueryDSL_Start_Main::queryDSL1);
        TransactionConsumer.consume(emf, Chap100402_QueryDSL_Start_Main::queryDSL2);
        emf.close();


    }



    private static void createData(EntityManager em) {

        MemberV2 member1 = MemberV2.builder()
                .name("wcchoi")
                .homeAddress(Address.builder()
                        .city("seoul")
                        .state("Korea")
                        .street("yeong-dong-dae-ro")
                        .zipcode(Zipcode.builder()
                                .zip("06080")
                                .plusFour("?")
                                .build()
                        )
                        .build()
                ).companyAddress(Address.builder()
                                .city("seoul")
                                .state("Korea")
                                .street("seoul-ro")
                                .zipcode(Zipcode.builder()
                                        .zip("06084")
                                        .plusFour("?")
                                        .build()
                                )
                                .build()
                ).phoneNumber(PhoneNumber.builder()
                        .areaCode("82")
                        .localNumber("1090487331")
                        .build()
                )
                .build();

        em.persist(member1);


        MemberV2 member2 = MemberV2.builder()
                .name("hymoon")
                .homeAddress(Address.builder()
                        .city("seoul")
                        .state("Korea")
                        .street("yeong-dong-dae-ro")
                        .zipcode(Zipcode.builder()
                                .zip("06080")
                                .plusFour("?")
                                .build()
                        )
                        .build()
                ).companyAddress(Address.builder()
                        .city("seoul")
                        .state("Korea")
                        .street("seoul-ro")
                        .zipcode(Zipcode.builder()
                                .zip("06084")
                                .plusFour("?")
                                .build()
                        )
                        .build()
                ).phoneNumber(PhoneNumber.builder()
                        .areaCode("82")
                        .localNumber("1092553405")
                        .build()
                )
                .build();

        em.persist(member2);


    }

    private static void queryDSL1(EntityManager em) {

        JPAQuery<MemberV2> query = new JPAQuery<>(em);
        QMemberV2 qMember = new QMemberV2("m"); // alias;

        List<MemberV2> members =
                query.from(qMember)
                .where(qMember.name.eq("wcchoi"))
                .orderBy(qMember.name.desc())
                .fetch();

        members.stream()
                .forEachOrdered(m-> System.out.println(m.getName()));
    }


    private static void queryDSL2(EntityManager em) {

        JPAQuery<MemberV2> query = new JPAQuery<>(em);

        List<com.querydsl.core.Tuple> members = query.select(memberV2.id, memberV2.name, memberV2.homeAddress, memberV2.phoneNumber)
                .from(memberV2)
                .where(memberV2.homeAddress.city.eq("seoul"))
                .orderBy(memberV2.phoneNumber.localNumber.asc())
                .fetch();

        members.stream()
                .forEachOrdered(t-> {
                    System.out.println(t.get(0, Long.class));
                    System.out.println(t.get(1, String.class));
                    System.out.println(t.get(2, Address.class));
                    System.out.println(t.get(3, PhoneNumber.class));
                });



    }

}
