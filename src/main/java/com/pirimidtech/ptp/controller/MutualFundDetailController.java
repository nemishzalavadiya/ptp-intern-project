package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.DTO.MutualFundStatisticDTO;
import com.pirimidtech.ptp.DTO.SelectedMutualFundFilter;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.repository.WatchlistEntryRepository;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import com.pirimidtech.ptp.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MutualFundDetailController {
    @Autowired
    MutualFundService mutualFundService;
    @Autowired
    private WatchlistService watchlistService;
    @Autowired
    private WatchlistEntryRepository watchlistEntryRepository;
    @Autowired
    private RequestUtil requestUtil;

    @GetMapping("/mutualfunds/details")
    public Page<MutualFundDetail> mutualFundsDetailList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<MutualFundDetail> mutualFundDetailList;
        mutualFundDetailList = mutualFundService.getAllMutualFundsDetails(paging);
        return mutualFundDetailList;
    }

    @GetMapping("/mutualfunds/details/{id}")
    public Optional<MutualFundDetail> mutualFundDetails(@PathVariable UUID id) {
        Optional<MutualFundDetail> mutualFundDetail = mutualFundService.getMutualFundDetailsById(id);
        if (!mutualFundDetail.isPresent())
            throw new NotFoundException();
        return mutualFundDetail;
    }

    @GetMapping("/mutualfunds/stats/{id}")
    public Optional<MutualFundStatistic> mutualFundStats(@PathVariable UUID id) {
        Optional<MutualFundStatistic> mutualFundStatistic = mutualFundService.getMutualFundStatsById(id);
        if (!mutualFundStatistic.isPresent())
            throw new NotFoundException();
        return mutualFundStatistic;
    }

    @PostMapping("/mutualfunds/filters")
    public Page<MutualFundStatisticDTO> fundsFilter(HttpServletRequest httpServletRequest, @RequestBody SelectedMutualFundFilter selectedMutualFundFilter, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String sortingField, @RequestParam(defaultValue = "") String orderBy) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Pageable paging = PageRequest.of(page, size);
        Page<MutualFundStatistic> mutualFundStatistics = mutualFundService.getMutualFundsFilterResults(selectedMutualFundFilter, paging, sortingField, orderBy);
        List<MutualFundStatistic> mutualFundStatisticList = mutualFundStatistics.toList();
        Watchlist watchlist = watchlistService.getWatchlistDetailByUserId(userId, AssetClass.MUTUAL_FUND);
        List<MutualFundStatisticDTO> mutualFundStatisticDTOList = new ArrayList<>();
        mutualFundStatisticList.forEach(element -> {
            Optional<WatchlistEntry> watchlistEntry = watchlistEntryRepository.findByAssetDetailIdAndAndWatchlistId(element.getMutualFundDetail().getAssetDetail().getId(), watchlist.getId());
            MutualFundStatisticDTO mutualFundStatisticDTO = new MutualFundStatisticDTO(element, watchlistEntry.isPresent());
            mutualFundStatisticDTOList.add(mutualFundStatisticDTO);
        });
        int contentSize = mutualFundStatisticDTOList.size();
        long total = paging.getOffset() + contentSize + (contentSize == size ? size : 0);
        return new PageImpl<MutualFundStatisticDTO>(mutualFundStatisticDTOList, paging, total);
    }
}
