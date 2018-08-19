package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.*;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ChildV4Id;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.GrandChildV4Id;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Chap0703_EmbeddedId_Identifying_MapsId_Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf,Chap0703_EmbeddedId_Identifying_MapsId_Main::test1);


        emf.close();
    }

    public static void test1(EntityManager em) {

        ParentV4 parent = new ParentV4();
        parent.setId("p1");
        parent.setName("Parent1");

        em.persist(parent);

        ChildV4 child = new ChildV4();
        ChildV4Id childId = new ChildV4Id();
        childId.setId("c1");
        childId.setParentId(parent.getId());
        child.setId(childId);
        child.setName("Child1");
        child.setParent(parent);

        em.persist(child);

        GrandChildV4 grandChild = new GrandChildV4();

        GrandChildV4Id grandChildId = new GrandChildV4Id();
        grandChildId.setId("gc1");
        grandChildId.setChildId(child.getId());

        grandChild.setId(grandChildId);
        grandChild.setChild(child);
        grandChild.setName("Grand Child 1");

        em.persist(grandChild);



    }
}
