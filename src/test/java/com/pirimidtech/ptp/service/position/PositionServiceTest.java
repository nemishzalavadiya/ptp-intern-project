package com.pirimidtech.ptp.service.position;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.Gender;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @MockBean
    private PositionRepository positionRepository;

    User user=new User(UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e"),"abc","abc@dev.com","","","","", Gender.MALE,"");
    @Test
    void getAllPosition() {
        CompanyDetail companyDetail=new CompanyDetail();
        companyDetail.setId(UUID.randomUUID());
        List<Position> positionList=new ArrayList<>();
        positionList.add(new Position(UUID.randomUUID(),35,100f, AssetClass.STOCK,user,companyDetail));
        positionList.add(new Position(UUID.randomUUID(),10,10f, AssetClass.MUTUAL_FUND,user,companyDetail));
        when(positionRepository.findAllByUserId(user.getId())).thenReturn(positionList);
        assertEquals(2,positionService.getAllPosition(user.getId()).size());

    }

    @Test
    void deleteFromPosition() {
        UUID positionId=UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e");
        positionService.deleteFromPosition(positionId);
        verify(positionRepository,times(1)).deleteById(positionId);
    }

    @Test
    void addToPosition() {
        CompanyDetail companyDetail=new CompanyDetail();
        companyDetail.setId(UUID.randomUUID());
        Position position=new Position(UUID.randomUUID(),35,100f, AssetClass.STOCK,user,companyDetail);
        positionService.addToPosition(position);
        verify(positionRepository,times(1)).save(position);
    }
}