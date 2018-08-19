package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.*;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ParentV1Id;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Chap0703_IdClass_Identifying_Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf,Chap0703_IdClass_Identifying_Main::test1);


        emf.close();
    }

    public static void test1(EntityManager em) {


        ParentV3 parent = new ParentV3();
        parent.setId("p1");
        parent.setName("Parent1");

        em.persist(parent);

        ChildV3 child = new ChildV3();
        child.setChildId("c1");
        child.setName("Child1");
        child.setParent(parent);

        em.persist(child);

        GrandChildV3 grandChild = new GrandChildV3();
        grandChild.setChild(child);
        grandChild.setId("gc1");
        grandChild.setName("Grand Child 1");

        em.persist(grandChild);
    }
}
