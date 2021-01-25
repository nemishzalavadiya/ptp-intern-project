package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.companyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface companyDetailRepository extends JpaRepository<companyDetail, UUID> {

}
