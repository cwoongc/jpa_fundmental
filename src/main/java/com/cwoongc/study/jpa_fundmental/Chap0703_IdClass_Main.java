package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.ChildV1;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.ParentV1;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ParentV1Id;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Chap0703_IdClass_Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf,Chap0703_IdClass_Main::test1);


        emf.close();
    }

    public static void test1(EntityManager em) {

        ParentV1 parent = new ParentV1();
        parent.setId1("id1");
        parent.setId2("id2");
        parent.setName("parentName");
        em.persist(parent);

        ParentV1Id parentId = new ParentV1Id("id1","id2");
        ParentV1 p = em.find(ParentV1.class, parentId);

        ChildV1 child = new ChildV1();
        child.setId("child1");
        child.setParent(p);

        em.persist(child);
    }
}
