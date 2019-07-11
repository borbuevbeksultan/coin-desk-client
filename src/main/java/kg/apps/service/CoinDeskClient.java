package kg.apps.service;

import kg.apps.dto.Currency;
import kg.apps.dto.CurrentBpiResponse;
import kg.apps.dto.HistoricalBpiResponse;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

public interface CoinDeskClient {
    HistoricalBpiResponse historical(String currencyCode, ZonedDateTime from, ZonedDateTime to) throws IOException, InterruptedException;

    CurrentBpiResponse current(String currencyCode) throws IOException, InterruptedException;

    List<Currency> supportedCurrencies() throws IOException, InterruptedException;
}
