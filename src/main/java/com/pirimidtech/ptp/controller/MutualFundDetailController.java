package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.exception.ExceptionHandler;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MutualFundDetailController {
    @Autowired
    MutualFundService mutualFundService;

    @GetMapping("/mutualfund/details")
    private List<MutualFundDetail> mutualFundsDetailList(){
        List<MutualFundDetail> mutualFundDetailList;
        try{
            mutualFundDetailList=mutualFundService.getAllMutualFundsDetails();
        } catch (Exception exception){
            throw new ExceptionHandler(exception.getCause());
        }
        return mutualFundDetailList;
    }

    @GetMapping("/mutualfund/details/{id}")
    private Optional<MutualFundDetail> mutualFundDetails(@PathVariable UUID id){
        Optional<MutualFundDetail> mutualFundDetail=mutualFundService.getMutualFundDetailsById(id);
        if(!mutualFundDetail.isPresent())
            throw new ExceptionHandler();
        return mutualFundDetail;
    }

    @GetMapping("/mutualfund/stats/{id}")
    private Optional<MutualFundStatistic> mutualFundStats(@PathVariable UUID id){
        Optional<MutualFundStatistic> mutualFundStatistic=mutualFundService.getMutualFundStatsById(id);
        if(!mutualFundStatistic.isPresent())
            throw new ExceptionHandler();
        return mutualFundStatistic;
    }
}
