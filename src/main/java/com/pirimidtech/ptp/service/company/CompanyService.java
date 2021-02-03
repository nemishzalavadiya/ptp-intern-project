package com.pirimidtech.ptp.service.company;
import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.repository.CompanyDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CompanyService implements CompanyServiceInterface{
    @Autowired
    private CompanyDetailRepository companyDetailRepository;
    @Override
    public List<CompanyDetail> getAllCompanyDetail(){
        return companyDetailRepository.findAll();
    }
    @Override
    public List<CompanyDetail> searchCompanyDetail(String infix){
        return companyDetailRepository.findByNameContainingIgnoreCase(infix);
    }
    @Override
    public Optional<CompanyDetail> getCompanyDetail(UUID id) {
        return companyDetailRepository.findById(id);
    }
    @Override
    public void addCompany(CompanyDetail companyDetail) {
        companyDetailRepository.save(companyDetail);
    }
}