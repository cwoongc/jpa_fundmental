package com.cwoongc.study.jpa_fundmental;

import com.cwoongc.study.jpa_fundmental.common.jpa.TransactionConsumer;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.ChildV4;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.GrandChildV4;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.ParentV4;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.ChildV4Id;
import com.cwoongc.study.jpa_fundmental.composite_key.entity.id.GrandChildV4Id;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Chap0703_OneToOne_Identifying_MapsId_Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p-unit");

        TransactionConsumer.consume(emf,Chap0703_OneToOne_Identifying_MapsId_Main::test1);


        emf.close();
    }

    public static void test1(EntityManager em) {





    }
}
