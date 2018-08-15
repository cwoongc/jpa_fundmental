package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.item.entity.ItemV2;
import com.cwoongc.study.jpa_fundmental.member.entity.MemberV2;
import com.cwoongc.study.jpa_fundmental.order.entity.OrderItemV2;
import com.cwoongc.study.jpa_fundmental.order.entity.OrderV2;
import com.cwoongc.study.jpa_fundmental.order.type.OrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;


public class Chap05ExMain {

    private static Long o1Id = null;

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit"); //Session Factory


        consume(emf, Chap05ExMain::createData);
        consume(emf, Chap05ExMain::searchObjGraph);

        emf.close();
    }


    private static void consume(EntityManagerFactory emf, java.util.function.Consumer<EntityManager> jpaConsumer) {
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

    private static void createData(EntityManager em) {

        MemberV2 m1 = new MemberV2();
        m1.setName("woong-chul");
        m1.setCity("seoul");
        m1.setStreet("30-7");
        m1.setZipcode("06080");

        em.persist(m1);

        ItemV2 i1 = new ItemV2();
        i1.setName("매일유업 우유 1L");
        i1.setPrice(3500);
        i1.setStockQuantity(150);

        em.persist(i1);

//////////////////////

        OrderV2 o1 = new OrderV2();
        o1.setOrderDate(new Date());
        o1.setOrderStatus(OrderStatus.ORDER);
        o1.setMember(m1);

        em.persist(o1);

        OrderItemV2 oi1 = new OrderItemV2();
        oi1.setCount(2);
        o1.addOrderItem(oi1);
        oi1.setItem(i1);
        oi1.setOrderPrice(oi1.getCount() * i1.getPrice());

        em.persist(oi1);

        o1Id = o1.getId();

    }







    private static void searchObjGraph(EntityManager em) {

        OrderV2 o1 = em.find(OrderV2.class, o1Id);

        MemberV2 m1 = o1.getMember();

        OrderItemV2 oi1 = o1.getOrderItems().get(0);

        ItemV2 i1 = oi1.getItem();



    }



}
