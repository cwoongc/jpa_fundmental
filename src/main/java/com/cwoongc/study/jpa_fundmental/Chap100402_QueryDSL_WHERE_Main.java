package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.MemberV2;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.QMemberV2;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.Address;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.PhoneNumber;
import com.cwoongc.study.jpa_fundmental.embedded_type.entity.value.Zipcode;
import com.cwoongc.study.jpa_fundmental.item.entity.*;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static com.cwoongc.study.jpa_fundmental.embedded_type.entity.QMemberV2.memberV2;

public class Chap100402_QueryDSL_WHERE_Main {
    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf, Chap100402_QueryDSL_WHERE_Main::createData);
        TransactionConsumer.consume(emf, Chap100402_QueryDSL_WHERE_Main::queryDSL1);
        TransactionConsumer.consume(emf, Chap100402_QueryDSL_WHERE_Main::queryDSL2);
        emf.close();


    }



    private static void createData(EntityManager em) {

        AlbumV4 album = AlbumV4.builder()
                .name("The Being")
                .artist("N.EX.T")
                .creator(1L)
                .price(15000)
                .updator(1L)
                .build();

        em.persist(album);

        MovieV4 movie = MovieV4.builder()
                .name("명량")
                .actor("최민식")
                .creator(1L)
                .director("김한민")
                .price(25000)
                .updator(1L)
                .build();


        em.persist(movie);

        BookV4 book = BookV4.builder()
                .author("오세현")
                .creator(1L)
                .isbn("978-89-475-4271-5")
                .name("블록체인노믹스")
                .price(17000)
                .updator(1L)
                .build();

        em.persist(book);


    }

    private static void queryDSL1(EntityManager em) {

        JPAQuery<ItemV4> query = new JPAQuery<>(em);
        QItemV4 item = QItemV4.itemV4;

        List<ItemV4> list = query.from(item)
                .where(item.name
                        .eq("블록체인노믹스")
                        .and(item.price.gt(15000))
                ).fetch();

        list.stream()
                .forEach(i -> System.out.println(String.format("%d:%s:%d",i.getId(),i.getName(),i.getPrice())));





    }


    private static void queryDSL2(EntityManager em) {




    }

}
