package kg.apps.service.impl;

import kg.apps.dto.Currency;
import kg.apps.service.CoinDeskClient;
import kg.apps.service.CurrencyService;

import java.util.List;

public class DefaultCurrencyService implements CurrencyService {

    private final CoinDeskClient coinDeskClient;
    private List<Currency> currencies;

    public DefaultCurrencyService() {
        this.coinDeskClient = new DefaultCoinDeskClient();
        try {
            currencies = coinDeskClient.supportedCurrencies();
        } catch (Exception e) {
            System.exit(0);
        }
    }

    @Override
    public Boolean isSupported(String currencyName) {
        return currencies
                .stream()
                .anyMatch(currency -> currencyName.equalsIgnoreCase(currency.getCurrency()));
    }

}
