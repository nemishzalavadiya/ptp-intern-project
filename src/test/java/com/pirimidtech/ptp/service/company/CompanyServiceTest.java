package com.pirimidtech.ptp.service.company;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.repository.CompanyDetailRepository;
import com.pirimidtech.ptp.service.company.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {
    @Mock
    CompanyDetailRepository companyDetailRepository;

    @InjectMocks
    CompanyService companyService;

    @Test
    public void testGetCompanyDetail(){
        CompanyDetail companyDetail = new CompanyDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), "name", "logo_url", AssetClass.STOCK, "about", "devesh", "org");
        when(companyDetailRepository.findById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"))).thenReturn(Optional.of(companyDetail));
        Optional<CompanyDetail> companyDetail1 = companyService.getCompanyDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"));
        assertEquals(companyDetail,companyDetail1.get());
    }

    @Test
    public void testGetAllCompanyDetail(){
        List<CompanyDetail> companyDetailList = new ArrayList<>();
        companyDetailList.add(new CompanyDetail(UUID.randomUUID(),"name", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        companyDetailList.add(new CompanyDetail(UUID.randomUUID(),"name", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        companyDetailList.add(new CompanyDetail(UUID.randomUUID(),"name", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        when(companyDetailRepository.findAll()).thenReturn(companyDetailList);
        assertEquals(companyDetailList,companyService.getAllCompanyDetail());
    }

    @Test
    public void testSearchCompanyList(){
        List<CompanyDetail> companyDetailList = new ArrayList<>();
        companyDetailList.add(new CompanyDetail(UUID.randomUUID(),"ptp1", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        companyDetailList.add(new CompanyDetail(UUID.randomUUID(),"ptp2", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        when(companyDetailRepository.findByNameContaining("ptp")).thenReturn(companyDetailList);
        assertEquals(companyService.searchCompanyDetail("ptp"), companyDetailList);
    }
}
