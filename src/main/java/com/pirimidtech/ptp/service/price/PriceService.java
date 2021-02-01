package com.pirimidtech.ptp.service.price;

import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.repository.MutualFundPriceRepository;
import com.pirimidtech.ptp.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PriceService implements PriceServiceInterface{

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Autowired
    private MutualFundPriceRepository mutualFundPriceRepository;


    @Override
    public void addToStockPrice(StockPrice stockPrice) {
        stockPriceRepository.save(stockPrice);
    }

    @Override
    public List<StockPrice> getStockPrice(UUID stockId) {
        return stockPriceRepository.findAllByStockId(stockId);
    }

    @Override
    public void addToMutualFundPrice(MutualFundPrice mutualFundPrice) {
            mutualFundPriceRepository.save(mutualFundPrice);
    }

    @Override
    public List<MutualFundPrice> getMutualFundPrice(UUID mutualFundId) {
    return mutualFundPriceRepository.findAllByMutualFundId(mutualFundId);

    }
}
