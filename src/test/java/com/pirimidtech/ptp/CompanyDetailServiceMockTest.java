package com.pirimidtech.ptp;

import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.repository.CompanyDetailRepository;
import com.pirimidtech.ptp.service.company.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDetailServiceMockTest {
    @Mock
    CompanyDetailRepository companyDetailRepository;

    @InjectMocks
    CompanyService companyService;

    @Test
    public void testGetCompanyDetail(){
        when(companyDetailRepository.findById()).thenReturn(new CompanyDetail(UUID.randomUUID(), "about", "STOCK", "logo_url", "devesh", "ptp", "org"))
    }
}
