package com.cwoongc.study.jpa_fundmental.common.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.function.Consumer;

public class TransactionConsumer {

    public static void consume(EntityManagerFactory emf, Consumer<EntityManager> jpaConsumer) {
        EntityManager em = emf.createEntityManager(); //Session, Do Not Share EntityManager!!

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            jpaConsumer.accept(em);
            tx.commit(); //flush


        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }


}
