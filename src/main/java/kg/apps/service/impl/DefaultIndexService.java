package kg.apps.service.impl;

import kg.apps.dto.HistoricalBpiResponse;
import kg.apps.service.IndexService;

import java.math.BigDecimal;

public class DefaultIndexService implements IndexService {

    @Override
    public BigDecimal lowestPrice(HistoricalBpiResponse bpi) {
        return bpi.getBpi().values().stream().min(BigDecimal::compareTo).orElseThrow(IllegalStateException::new);
    }

    @Override
    public BigDecimal highestPrice(HistoricalBpiResponse bpi) {
        return bpi.getBpi().values().stream().max(BigDecimal::compareTo).orElseThrow(IllegalStateException::new);
    }

}
