package com.cwoongc.study.jpa_fundmental.embedded_type.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberV2Repository extends JpaRepository<com.cwoongc.study.jpa_fundmental.embedded_type.entity.MemberV2,Long> {

    List<com.cwoongc.study.jpa_fundmental.embedded_type.entity.MemberV2> findByNameAndHomeAddress_City(String name, String homeAddressCity);

}
