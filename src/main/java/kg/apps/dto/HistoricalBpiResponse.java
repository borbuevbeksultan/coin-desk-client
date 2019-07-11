package kg.apps.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class HistoricalBpiResponse {
    private Map<String, BigDecimal> bpi;
}
