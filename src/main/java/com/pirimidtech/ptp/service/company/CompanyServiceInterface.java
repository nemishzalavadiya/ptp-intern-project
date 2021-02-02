package com.pirimidtech.ptp.service.company;
import com.pirimidtech.ptp.entity.CompanyDetail;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface CompanyServiceInterface {
    Optional<CompanyDetail> getCompanyDetail(UUID id);

    void addCompany(CompanyDetail companyDetail);

    List<CompanyDetail> getAllCompanyDetail();

    List<CompanyDetail> searchCompanyDetail(String infix);
}