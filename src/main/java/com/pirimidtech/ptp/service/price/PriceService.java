package com.pirimidtech.ptp.service.price;

import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.repository.MutualFundPriceRepository;
import com.pirimidtech.ptp.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<StockPrice> getStockPrice(UUID stockId, String startDate, String endDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = simpleDateFormat.parse(startDate);
            date2 = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            throw e;
        }
        List<StockPrice> stockPriceList = stockPriceRepository.findAllByStockDetailIdAndTimestampBetween(stockId, date1, date2);
        return stockPriceList;
    }

    @Override
    public void addToMutualFundPrice(MutualFundPrice mutualFundPrice) {
        mutualFundPriceRepository.save(mutualFundPrice);
    }

    @Override
    public List<MutualFundPrice> getMutualFundPrice(UUID mutualFundId, String startDate, String endDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = simpleDateFormat.parse(startDate);
            date2 = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            throw e;
        }
        List<MutualFundPrice> mutualFundPriceList = mutualFundPriceRepository.findAllByMutualFundDetailIdAndTimestampBetween(mutualFundId, date1, date2);
        return mutualFundPriceList;
    }
}
