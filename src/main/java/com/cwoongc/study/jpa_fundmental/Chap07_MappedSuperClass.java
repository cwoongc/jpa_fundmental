package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.member.entity.Member;
import com.cwoongc.study.jpa_fundmental.member.entity.MemberV5;
import com.cwoongc.study.jpa_fundmental.member.entity.SellerV5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Chap07_MappedSuperClass {

    public static final Long CREATOR = 1L;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf,Chap07_MappedSuperClass::checkMappedSuperClass);
        TransactionConsumer.consume(emf,Chap07_MappedSuperClass::checkMappedSuperClass2);
        TransactionConsumer.consume(emf,Chap07_MappedSuperClass::checkMappedSuperClass3);

        emf.close();
    }

    private static void checkMappedSuperClass(EntityManager em) {


        SellerV5 seller1 = SellerV5.builder()
                .creator(CREATOR)
                .name("ps&m")
                .shopName("tworld direct")
                .build();

        em.persist(seller1);


        MemberV5 member1 = MemberV5.builder()
                .creator(CREATOR)
                .name("wcchoi")
                .email("wcchoi@sk.com")
                .build();

        em.persist(member1);

    }



    private static void checkMappedSuperClass2(EntityManager em) {

        SellerV5 seller2 = SellerV5.builder()
                .creator(CREATOR)
                .name("Star Bucks")
                .shopName("COEX BRANCH")
                .build();

        em.persist(seller2);


        MemberV5 memberV5 = MemberV5.builder()
                .creator(CREATOR)
                .name("sjchoi")
                .email("sjchoi@wc.com")
                .build();

        em.persist(memberV5);



        em.flush();
        em.clear();



    }

    private static void checkMappedSuperClass3(EntityManager em) {
        SellerV5 s1 = em.find(SellerV5.class,1L);
        System.out.println(s1);
        MemberV5 m1 = em.find(MemberV5.class,2L);
        System.out.println(m1);
        SellerV5 s2 = em.find(SellerV5.class, 3L);
        System.out.println(s2);
        MemberV5 m2 = em.find(MemberV5.class, 4L);
        System.out.println(m2);


        List<SellerV5> sellerV5s = em.createQuery("select s from SellerV5 s where s.shopName is not null",SellerV5.class)
                .getResultList();

        sellerV5s.forEach(s-> System.out.println(s));
    }


}
