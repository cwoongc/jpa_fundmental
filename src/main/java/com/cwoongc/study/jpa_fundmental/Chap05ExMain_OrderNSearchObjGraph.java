package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.item.entity.ItemV2;
import com.cwoongc.study.jpa_fundmental.member.entity.MemberV2_;
import com.cwoongc.study.jpa_fundmental.order.entity.OrderItemV2;
import com.cwoongc.study.jpa_fundmental.order.entity.OrderV2;
import com.cwoongc.study.jpa_fundmental.order.type.OrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;


public class Chap05ExMain_OrderNSearchObjGraph {

    private static Long o1Id = null;

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit"); //Session Factory


        TransactionConsumer.consume(emf, Chap05ExMain_OrderNSearchObjGraph::createData);
        TransactionConsumer.consume(emf, Chap05ExMain_OrderNSearchObjGraph::searchObjGraph);

        emf.close();
    }

    private static void createData(EntityManager em) {

        MemberV2_ m1 = new MemberV2_();
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

        /**
         * 주문 시작
         */

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

        MemberV2_ m1 = o1.getMember();

        OrderItemV2 oi1 = o1.getOrderItems().get(0);

        ItemV2 i1 = oi1.getItem();



    }



}
