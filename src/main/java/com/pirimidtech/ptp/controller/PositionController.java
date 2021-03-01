package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.DTO.MutualFundPositionDTO;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private MutualFundService mutualFundService;

    @GetMapping("/position/assets/stock/users/{id}")
    public ResponseEntity<Page<Position>> getStockPositionByUser(@PathVariable("id") UUID userId,@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size)
    {
        Page<Position> positionList = positionService.getPositionByAssetClass(userId,AssetClass.STOCK, page, size);
        return ResponseEntity.ok().body(positionList);
    }

    @GetMapping("/position/assets/mutualFund/users/{id}")
    public ResponseEntity<Page<MutualFundPositionDTO>> getMutualFundPositionByUser(@PathVariable("id") UUID userId,@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size)
    {
        List<MutualFundPositionDTO> mutualFundPositionDTOList=new ArrayList<>();
        Page<Position> positionList=positionService.getPositionByAssetClass(userId,AssetClass.MUTUAL_FUND,page,size);
        positionList.forEach(position ->{
            Optional<MutualFundStatistic> mutualFundStatisticOptional =mutualFundService.getMutualFundStatisticByAssetId(position.getAssetDetail().getId());
            if(mutualFundStatisticOptional.isPresent()) {
                MutualFundStatistic mutualFundStatistic = mutualFundStatisticOptional.get();
                float nav = mutualFundStatistic.getNav();
                float netValue = position.getVolume() * nav;
                float profit = ((netValue - position.getVolume() * position.getPrice()) / (position.getVolume() * position.getPrice())) * 100;
                MutualFundPositionDTO mutualFundPositionDTO = new MutualFundPositionDTO(position, nav, netValue, profit);
                mutualFundPositionDTOList.add(mutualFundPositionDTO);
            }
        });
        Page<MutualFundPositionDTO> result =new PageImpl<MutualFundPositionDTO>(mutualFundPositionDTOList,positionList.getPageable(),positionList.getTotalPages());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/position/search/assets/stock/users/{id}")
    public ResponseEntity<Page<Position>> searchInStockPosition(@PathVariable("id") UUID userId,@RequestParam("name") String name,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Position> search = positionService.searchByAssetClassAndAssetDetailName(userId,name,AssetClass.STOCK,page,size);
        return ResponseEntity.ok().body(search);
    }

    @GetMapping("/position/search/assets/mutualFund/users/{id}")
    public ResponseEntity<Page<MutualFundPositionDTO>> searchInMutualFundPosition(@PathVariable("id") UUID userId,@RequestParam("name") String name,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        List<MutualFundPositionDTO> mutualFundPositionDTOList=new ArrayList<>();
        Page<Position> positionList=positionService.searchByAssetClassAndAssetDetailName(userId,name,AssetClass.MUTUAL_FUND,page,size);

        positionList.toList().forEach(position ->{
            Optional<MutualFundStatistic> mutualFundStatisticOptional =mutualFundService.getMutualFundStatisticByAssetId(position.getAssetDetail().getId());
            if(mutualFundStatisticOptional.isPresent()) {
                MutualFundStatistic mutualFundStatistic = mutualFundStatisticOptional.get();
                float nav = mutualFundStatistic.getNav();
                float netValue = position.getVolume() * nav;
                float profit = ((netValue - position.getVolume() * position.getPrice()) / (position.getVolume() * position.getPrice())) * 100;
                MutualFundPositionDTO mutualFundPositionDTO = new MutualFundPositionDTO(position, nav, netValue, profit);
                mutualFundPositionDTOList.add(mutualFundPositionDTO);
            }
        });
        Pageable pageable=positionList.getPageable();
        int contentSize=positionList.toList().size();
        long total = pageable.getOffset() + contentSize + (contentSize == size ? size : 0);
        Page<MutualFundPositionDTO> result =new PageImpl<MutualFundPositionDTO>(mutualFundPositionDTOList,positionList.getPageable(),total);
        return ResponseEntity.ok().body(result);
    }


}