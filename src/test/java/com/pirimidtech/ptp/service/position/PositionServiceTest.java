package com.pirimidtech.ptp.service.position;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.repository.PositionRepository;
import com.pirimidtech.ptp.utility.ObjectUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PositionServiceTest {

    User user = ObjectUtility.user;
    @Autowired
    private PositionService positionService;
    @MockBean
    private PositionRepository positionRepository;

    @Test
    void getPositionByAssetClass() {
        AssetDetail assetDetail = ObjectUtility.assetDetail;
        List<Position> positionList = new ArrayList<>();
        positionList.add(ObjectUtility.position);
        when(positionRepository.findByUserIdAndAndAssetClass(user.getId(), AssetClass.STOCK ,PageRequest.of(0, 1))).thenReturn(new PageImpl<>(positionList));
        assertEquals(1, positionService.getPositionByAssetClass(user.getId(), AssetClass.STOCK,0, 1).toList().size());
    }

    @Test
    void deleteFromPosition() {
        UUID positionId = ObjectUtility.position.getId();
        positionService.deleteFromPosition(positionId);
        verify(positionRepository, times(1)).deleteById(positionId);
    }

    @Test
    void addMutualFundToPosition() {
        AssetDetail assetDetail = ObjectUtility.assetDetail;
        Position position = ObjectUtility.position;
        positionService.addMutualFundToPosition(position);
        verify(positionRepository, times(1)).save(position);
    }

    @Test
    void addStockToPosition() {
        AssetDetail assetDetail = ObjectUtility.assetDetail;
        Position position = ObjectUtility.position;
        positionService.addStockToPosition(position,Action.BUY);
        verify(positionRepository, times(1)).save(position);
        verify(positionRepository, times(1)).save(position);
    }

    @Test
    void searchByAssetClassAndAssetDetailName() {
        AssetDetail assetDetail = ObjectUtility.assetDetail;
        List<Position> positionList = new ArrayList<>();
        positionList.add(ObjectUtility.position);
        when(positionRepository.findByUserIdAndAndAssetClassAndAndAssetDetailNameContainingIgnoreCase(ObjectUtility.user.getId(),AssetClass.STOCK ,"a",PageRequest.of(0, 1))).thenReturn(new PageImpl<>(positionList));
        assertEquals(1, positionService.searchByAssetClassAndAssetDetailName(ObjectUtility.user.getId(),"a",AssetClass.STOCK,0, 1).toList().size());
    }
}