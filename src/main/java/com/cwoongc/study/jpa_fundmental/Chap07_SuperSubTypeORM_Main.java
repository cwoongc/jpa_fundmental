package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.item.entity.AlbumV4;
import com.cwoongc.study.jpa_fundmental.item.entity.MovieV4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Chap07_SuperSubTypeORM_Main {

    private static final Long CREATOR_ID = 7331L;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf,Chap07_SuperSubTypeORM_Main::checkSingleTableExclusiveInsertionNSelection);

        emf.close();

    }


    private static void checkSingleTableExclusiveInsertionNSelection(EntityManager em) {

//        AlbumV4 album1 = new AlbumV4(
//                "The Being",
//                12000,
//                "N.EX.T"
//        );

        AlbumV4 album1 = AlbumV4.builder()
                .name("The Being")
                .artist("N.EX.T")
                .price(12000)
                .creator(CREATOR_ID)
                .updator(CREATOR_ID)
                .build();


        em.persist(album1);

        MovieV4 movie1 = MovieV4.builder()
                .name("Avangers")
                .actor("Chris Hamthworth")
                .director("unknown")
                .price(25000)
                .creator(CREATOR_ID)
                .updator(CREATOR_ID)
                .build();

        em.persist(movie1);

        em.flush();
        em.clear();



        MovieV4 movie2 = em.find(MovieV4.class,2L);

        System.out.println(movie2.toString());


    }



}
