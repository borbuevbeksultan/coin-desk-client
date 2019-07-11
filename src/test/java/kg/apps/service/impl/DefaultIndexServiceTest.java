package kg.apps.service.impl;

import kg.apps.dto.HistoricalBpiResponse;
import kg.apps.service.IndexService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class DefaultIndexServiceTest {

    private IndexService indexService;

    @Before
    public void init() {
        indexService = new DefaultIndexService();
    }

    @Test
    public void lowestPrice_providedMockData_shouldBeEqualOnePointFive() {
        //arrange
        BigDecimal expectedLowestRate = BigDecimal.valueOf(1.5);
        HistoricalBpiResponse historicalBpiResponse = mockHistoricalData();
        //act
        BigDecimal actualLowestPrice = indexService.lowestPrice(historicalBpiResponse);

        //assert
        Assertions.assertEquals(0, actualLowestPrice.compareTo(expectedLowestRate));
    }

    @Test
    public void highestPrice_providedMockData_shouldBeEqualSevenPointFive() {
        //arrange
        BigDecimal expectedHighestRate = BigDecimal.valueOf(7.5);
        HistoricalBpiResponse historicalBpiResponse = mockHistoricalData();
        //act
        BigDecimal actualLowestPrice = indexService.highestPrice(historicalBpiResponse);

        //assert
        Assertions.assertEquals(0, actualLowestPrice.compareTo(expectedHighestRate));
    }

    public void destroy() {
        indexService = null;
    }

    public static HistoricalBpiResponse mockHistoricalData() {
        Map<String, BigDecimal> ratesInLast30Days = new HashMap<>();
        ratesInLast30Days.put("18-12-232321", BigDecimal.valueOf(2.4));
        ratesInLast30Days.put("17-12-232321", BigDecimal.valueOf(1.6));
        ratesInLast30Days.put("16-12-232321", BigDecimal.valueOf(3.1));
        ratesInLast30Days.put("15-12-232321", BigDecimal.valueOf(7.5));
        ratesInLast30Days.put("14-12-232321", BigDecimal.valueOf(1.5));
        ratesInLast30Days.put("13-12-232321", BigDecimal.valueOf(6.4));
        ratesInLast30Days.put("12-12-232321", BigDecimal.valueOf(2.1));
        ratesInLast30Days.put("11-12-232321", BigDecimal.valueOf(7.4));

        HistoricalBpiResponse historicalBpiResponse = new HistoricalBpiResponse();
        historicalBpiResponse.setBpi(ratesInLast30Days);
        return historicalBpiResponse;
    }

}