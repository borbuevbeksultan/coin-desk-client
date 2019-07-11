package kg.apps;

import kg.apps.dto.CurrentBpiResponse;
import kg.apps.dto.HistoricalBpiResponse;
import kg.apps.service.CoinDeskClient;
import kg.apps.service.CurrencyService;
import kg.apps.service.IndexService;
import kg.apps.service.impl.DefaultCoinDeskClient;
import kg.apps.service.impl.DefaultCurrencyService;
import kg.apps.service.impl.DefaultIndexService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class BpiProvider {

    private final CurrencyService currencyService;
    private final CoinDeskClient coinDeskClient;
    private final IndexService indexService;

    public BpiProvider() {
        this.currencyService = new DefaultCurrencyService();
        this.coinDeskClient = new DefaultCoinDeskClient();
        this.indexService = new DefaultIndexService();
    }

    public void provideForCurrency(String currencyCode) throws IOException, InterruptedException {

        if (!currencyService.isSupported(currencyCode)) {
            System.out.println("Unknown currency code");
            System.exit(0);
        }

        CurrentBpiResponse current = coinDeskClient.current(currencyCode);

        ZonedDateTime endDate = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime startDate = endDate.minusMonths(30);

        HistoricalBpiResponse historical = coinDeskClient.historical(currencyCode, startDate, endDate);

        BigDecimal highestPrice = indexService.highestPrice(historical);
        BigDecimal lowestPrice = indexService.lowestPrice(historical);

        System.out.println(String.format("The current Bitcoin rate in %s currency is: %s", currencyCode, current.getBpi().get(currencyCode).getRate()));
        System.out.println(String.format("The highest Bitcoin rate in the last 30 days in %s currency is: %s",currencyCode, highestPrice));
        System.out.println(String.format("The lowest Bitcoin rate in the last 30 days in %s currency is: %s", currencyCode, lowestPrice));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0) {
            System.out.println("No currency provided");
        } else {
            BpiProvider bpiProvider = new BpiProvider();
            bpiProvider.provideForCurrency(args[0]);
        }
    }

}
