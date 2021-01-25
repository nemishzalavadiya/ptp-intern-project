package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.companyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CompanyDetailRepository extends CrudRepository<companyDetail, UUID> {

}
