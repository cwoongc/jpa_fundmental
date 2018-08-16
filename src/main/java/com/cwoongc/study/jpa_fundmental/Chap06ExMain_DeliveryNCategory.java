package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Chap06ExMain_DeliveryNCategory {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit"); //Session Factory


        TransactionConsumer.consume(emf,Chap06ExMain_DeliveryNCategory::createMember);
        TransactionConsumer.consume(emf,Chap06ExMain_DeliveryNCategory::createItem);
        TransactionConsumer.consume(emf,Chap06ExMain_DeliveryNCategory::createCategory);
        TransactionConsumer.consume(emf,Chap06ExMain_DeliveryNCategory::createOrderNDelivery);

        emf.close();
    }



    private static void createMember(EntityManager em) {



    }

    private static void createItem(EntityManager em) {

    }


    private static void createCategory(EntityManager em) {

    }

    private static void createOrderNDelivery(EntityManager em) {

    }

}
