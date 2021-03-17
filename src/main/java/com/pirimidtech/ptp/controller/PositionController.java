package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.DTO.MutualFundPositionDTO;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.position.PositionService;
import com.pirimidtech.ptp.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private RequestUtil requestUtil;

    @GetMapping("/stock/position")
    public ResponseEntity<Page<Position>> getStockPositionByUser(HttpServletRequest httpServletRequest, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Page<Position> positionList = positionService.getPositionByAssetClass(userId, AssetClass.STOCK, page, size);
        return ResponseEntity.ok().body(positionList);
    }

    @GetMapping("/mutualfund/position")
    public ResponseEntity<Page<MutualFundPositionDTO>> getMutualFundPositionByUser(HttpServletRequest httpServletRequest, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        List<MutualFundPositionDTO> mutualFundPositionDTOList = new ArrayList<>();
        Page<Position> positionList = positionService.getPositionByAssetClass(userId, AssetClass.MUTUAL_FUND, page, size);
        positionList.forEach(position -> {
            Optional<MutualFundStatistic> mutualFundStatisticOptional = mutualFundService.getMutualFundStatisticByAssetId(position.getAssetDetail().getId());
            if (mutualFundStatisticOptional.isPresent()) {
                MutualFundStatistic mutualFundStatistic = mutualFundStatisticOptional.get();
                float nav = mutualFundStatistic.getNav();
                float netValue = position.getVolume() * nav;
                float profitPercentage = ((netValue - position.getPrice()) / (position.getPrice())) * 100;
                float avgNav = position.getPrice() / position.getVolume();
                MutualFundPositionDTO mutualFundPositionDTO = new MutualFundPositionDTO(position.getAssetDetail().getName(), position.getAssetDetail().getId(), position.getVolume(), position.getPrice(), nav, netValue, profitPercentage, avgNav, netValue - position.getPrice());
                mutualFundPositionDTOList.add(mutualFundPositionDTO);
            }
        });
        Page<MutualFundPositionDTO> result = new PageImpl<MutualFundPositionDTO>(mutualFundPositionDTOList, positionList.getPageable(), positionList.getTotalPages());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/stock/position/search")
    public ResponseEntity<Page<Position>> searchInStockPosition(HttpServletRequest httpServletRequest, @RequestParam("name") String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Page<Position> search = positionService.searchByAssetClassAndAssetDetailName(userId, name, AssetClass.STOCK, page, size);
        return ResponseEntity.ok().body(search);
    }

    @GetMapping("/mutualfund/position/search")
    public ResponseEntity<Page<MutualFundPositionDTO>> searchInMutualFundPosition(HttpServletRequest httpServletRequest, @RequestParam("name") String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);

        List<MutualFundPositionDTO> mutualFundPositionDTOList = new ArrayList<>();
        Page<Position> positionList = positionService.searchByAssetClassAndAssetDetailName(userId, name, AssetClass.MUTUAL_FUND, page, size);

        positionList.toList().forEach(position -> {
            Optional<MutualFundStatistic> mutualFundStatisticOptional = mutualFundService.getMutualFundStatisticByAssetId(position.getAssetDetail().getId());
            if (mutualFundStatisticOptional.isPresent()) {
                MutualFundStatistic mutualFundStatistic = mutualFundStatisticOptional.get();
                float nav = mutualFundStatistic.getNav();
                float netValue = position.getVolume() * nav;
                float profitPercentage = ((netValue - position.getPrice()) / (position.getPrice())) * 100;
                float avgNav = position.getPrice() / position.getVolume();
                MutualFundPositionDTO mutualFundPositionDTO = new MutualFundPositionDTO(position.getAssetDetail().getName(), position.getAssetDetail().getId(), position.getVolume(), position.getPrice(), nav, netValue, profitPercentage, avgNav, netValue - position.getPrice());
                mutualFundPositionDTOList.add(mutualFundPositionDTO);
            }
        });
        Pageable pageable = positionList.getPageable();
        int contentSize = positionList.toList().size();
        long total = pageable.getOffset() + contentSize + (contentSize == size ? size : 0);
        Page<MutualFundPositionDTO> result = new PageImpl<MutualFundPositionDTO>(mutualFundPositionDTOList, positionList.getPageable(), total);
        return ResponseEntity.ok().body(result);
    }


}