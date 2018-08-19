package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.ChildV1;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.ParentV1;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.ParentV2;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ParentV1Id;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ParentV2Id;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Chap0703_EmbeddedId_Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf,Chap0703_EmbeddedId_Main::test1);


        emf.close();
    }

    public static void test1(EntityManager em) {

        ParentV2 parent  = new ParentV2();
        ParentV2Id parentId = new ParentV2Id("id1","id2");
        parent.setId(parentId);
        parent.setName("parentName");
        em.persist(parent);

        ParentV2Id pid = new ParentV2Id("id1","id2");
        ParentV2 p = em.find(ParentV2.class, parentId);



    }
}
