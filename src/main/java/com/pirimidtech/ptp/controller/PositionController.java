package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.DTO.MutualFundPositionDTO;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private MutualFundService mutualFundService;

    @GetMapping("/stock/position/users/{id}")
    public ResponseEntity<Page<Position>> getStockPositionByUser(@PathVariable("id") UUID userId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Position> positionList = positionService.getPositionByAssetClass(userId, AssetClass.STOCK, page, size);
        return ResponseEntity.ok().body(positionList);
    }

    @GetMapping("/mutualfund/position/users/{id}")
    public ResponseEntity<Page<MutualFundPositionDTO>> getMutualFundPositionByUser(@PathVariable("id") UUID userId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        List<MutualFundPositionDTO> mutualFundPositionDTOList = new ArrayList<>();
        Page<Position> positionList = positionService.getPositionByAssetClass(userId, AssetClass.MUTUAL_FUND, page, size);
        positionList.forEach(position -> {
            Optional<MutualFundStatistic> mutualFundStatisticOptional = mutualFundService.getMutualFundStatisticByAssetId(position.getAssetDetail().getId());
            if (mutualFundStatisticOptional.isPresent()) {
                MutualFundStatistic mutualFundStatistic = mutualFundStatisticOptional.get();
                float nav = mutualFundStatistic.getNav();
                float netValue = position.getVolume() * nav;
                float profitPercentage = ((netValue - position.getPrice()) / (position.getPrice())) * 100;
                float avgNav=position.getPrice()/position.getVolume();
                MutualFundPositionDTO mutualFundPositionDTO = new MutualFundPositionDTO(position.getAssetDetail().getName(), position.getAssetDetail().getId(),position.getVolume(),position.getPrice(),nav, netValue, profitPercentage,avgNav,netValue-position.getPrice());
                mutualFundPositionDTOList.add(mutualFundPositionDTO);
            }
        });
        Page<MutualFundPositionDTO> result = new PageImpl<MutualFundPositionDTO>(mutualFundPositionDTOList, positionList.getPageable(), positionList.getTotalPages());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/stock/position/search/users/{id}")
    public ResponseEntity<Page<Position>> searchInStockPosition(@PathVariable("id") UUID userId, @RequestParam("name") String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Position> search = positionService.searchByAssetClassAndAssetDetailName(userId, name, AssetClass.STOCK, page, size);
        return ResponseEntity.ok().body(search);
    }

    @GetMapping("/mutualfund/position/search/users/{id}")
    public ResponseEntity<Page<MutualFundPositionDTO>> searchInMutualFundPosition(@PathVariable("id") UUID userId, @RequestParam("name") String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,  @RequestParam(defaultValue = "") String sortingField, @RequestParam(defaultValue = "") String orderBy) {

        List<MutualFundPositionDTO> mutualFundPositionDTOList = new ArrayList<>();
        Page<Position> positionList = positionService.searchByAssetClassAndAssetDetailName(userId, name, AssetClass.MUTUAL_FUND, page, size);

        positionList.toList().forEach(position -> {
            Optional<MutualFundStatistic> mutualFundStatisticOptional = mutualFundService.getMutualFundStatisticByAssetId(position.getAssetDetail().getId());
            if (mutualFundStatisticOptional.isPresent()) {
                MutualFundStatistic mutualFundStatistic = mutualFundStatisticOptional.get();
                float nav = mutualFundStatistic.getNav();
                float netValue = position.getVolume() * nav;
                float profitPercentage = ((netValue - position.getPrice()) / (position.getPrice())) * 100;
                float avgNav=position.getPrice()/position.getVolume();
                MutualFundPositionDTO mutualFundPositionDTO = new MutualFundPositionDTO(position.getAssetDetail().getName(), position.getAssetDetail().getId(),position.getVolume(),position.getPrice(),nav, netValue, profitPercentage,avgNav,netValue-position.getPrice());
                mutualFundPositionDTOList.add(mutualFundPositionDTO);
            }
        });
        if(orderBy.equals("ASC") && sortingField.equals("Current Value")) {
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getNetValue));
        }
        else if(orderBy.equals("DESC") && sortingField.equals("Current Value")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getNetValue).reversed());
        }
        else if(orderBy.equals("ASC") && sortingField.equals("current NAV")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getNav));
        }
        else if(orderBy.equals("DESC") && sortingField.equals("current NAV")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getNav).reversed());
        }
        else if(orderBy.equals("ASC") && sortingField.equals("Average NAV")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getAvgNav));
        }
        else if(orderBy.equals("DESC") && sortingField.equals("Average NAV")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getAvgNav).reversed());
        }
        else if(orderBy.equals("ASC") && sortingField.equals("Profit & Loss")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getProfit));
        }
        else if(orderBy.equals("DESC") && sortingField.equals("Profit & Loss")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getProfit).reversed());
        }
        else if(orderBy.equals("ASC") && sortingField.equals("Company")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getAssetName));
        }
        else if(orderBy.equals("DESC") && sortingField.equals("Company")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getAssetName).reversed());
        }
        else if(orderBy.equals("ASC") && sortingField.equals("Quantity")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getQuantity));
        }
        else if(orderBy.equals("DESC") && sortingField.equals("Quantity")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getQuantity).reversed());
        }
        else if(orderBy.equals("ASC") && sortingField.equals("Total Amount")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getTotalAmount));
        }
        else if(orderBy.equals("DESC") && sortingField.equals("Total Amount")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getTotalAmount).reversed());
        }
        else if(orderBy.equals("ASC") && sortingField.equals("Profit & Loss(%)")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getProfitPercentage));
        }
        else if(orderBy.equals("DESC") && sortingField.equals("Profit & Loss(%)")){
            mutualFundPositionDTOList.sort(Comparator.comparing(MutualFundPositionDTO::getProfitPercentage).reversed());
        }
        Pageable pageable = positionList.getPageable();
        int contentSize = positionList.toList().size();
        long total = pageable.getOffset() + contentSize + (contentSize == size ? size : 0);
        Page<MutualFundPositionDTO> result = new PageImpl<MutualFundPositionDTO>(mutualFundPositionDTOList, positionList.getPageable(), total);
        return ResponseEntity.ok().body(result);
    }


}