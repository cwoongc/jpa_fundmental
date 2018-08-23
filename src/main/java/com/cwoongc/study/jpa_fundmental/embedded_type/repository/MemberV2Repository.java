package com.cwoongc.study.jpa_fundmental.embedded_type.repository;

import com.cwoongc.study.jpa_fundmental.embedded_type.entity.MemberV2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberV2Repository extends JpaRepository<com.cwoongc.study.jpa_fundmental.embedded_type.entity.MemberV2,Long> {

    List<MemberV2> findByNameAndHomeAddress_City(String name, String homeAddressCity);

    Page<MemberV2> findByNameStartingWith(String name, Pageable pageable);

}
