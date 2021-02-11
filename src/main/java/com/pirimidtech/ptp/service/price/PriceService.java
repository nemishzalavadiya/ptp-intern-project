package com.pirimidtech.ptp.service.price;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.repository.MutualFundPriceRepository;
import com.pirimidtech.ptp.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PriceService implements PriceServiceInterface {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Autowired
    private MutualFundPriceRepository mutualFundPriceRepository;


    @Override
    public void addToStockPrice(StockPrice stockPrice) {
        stockPriceRepository.save(stockPrice);
    }

    @Override
    public List<StockPrice> getStockPrice(UUID stockId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<StockPrice> pageResult = stockPriceRepository.findAllByStockDetailIdOrderByTimestampDesc(stockId, pageable);
        return pageResult.toList();
    }

    @Override
    public void addToMutualFundPrice(MutualFundPrice mutualFundPrice) {
        mutualFundPriceRepository.save(mutualFundPrice);
    }

    @Override
    public List<MutualFundPrice> getMutualFundPrice(UUID mutualFundId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<MutualFundPrice> pageResult = mutualFundPriceRepository.findAllByMutualFundDetailIdOrderByTimestampDesc(mutualFundId, pageable);
        return pageResult.toList();
    }
}
