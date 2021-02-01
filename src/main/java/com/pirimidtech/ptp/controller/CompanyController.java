package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, value = "/company/{id}")
    public Optional<CompanyDetail> getCompanyDetail(@PathVariable UUID id){
        return companyService.getCompanyDetail(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/company")
    public void addCompanyDetail(@RequestBody CompanyDetail companyDetail){
        companyService.addCompany(companyDetail);
    }

    @GetMapping(value = "/company")
    public List<CompanyDetail> companyDetailList(){
        return companyService.getAllCompanyDetail();
    }

    @GetMapping(value = "/company/search/{infix}")
    public List<CompanyDetail> searchComapanyList(String infix){
        return companyService.searchCompanydetail(infix);
    }
}
