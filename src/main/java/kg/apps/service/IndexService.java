package kg.apps.service;

import kg.apps.dto.HistoricalBpiResponse;

import java.math.BigDecimal;

public interface IndexService {
    BigDecimal lowestPrice(HistoricalBpiResponse bpi);

    BigDecimal highestPrice(HistoricalBpiResponse bpi);
}
