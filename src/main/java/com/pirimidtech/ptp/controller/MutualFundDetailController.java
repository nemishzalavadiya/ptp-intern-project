package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class MutualFundDetailController {
    @Autowired
    MutualFundService mutualFundService;

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
}
