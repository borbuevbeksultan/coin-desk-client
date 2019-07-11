package kg.apps.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kg.apps.dto.Currency;
import kg.apps.dto.CurrentBpiResponse;
import kg.apps.dto.HistoricalBpiResponse;
import kg.apps.service.CoinDeskClient;
import kg.apps.utils.DateUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DefaultCoinDeskClient implements CoinDeskClient {

    private final String historicalRateUri;
    private final String currentRateUri;
    private final String supportedCurrenciesUri;

    private HttpClient httpClient;
    private Gson gson;

    public DefaultCoinDeskClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("application", Locale.getDefault());

        historicalRateUri = resourceBundle.getString("coin-desk.historical-rate-uri");
        currentRateUri = resourceBundle.getString("coin-desk.current-rate-uri");
        supportedCurrenciesUri = resourceBundle.getString("coin-desk.supported-currencies-uri");
    }

    @Override
    public HistoricalBpiResponse historical(String currencyCode, ZonedDateTime from, ZonedDateTime to) throws IOException, InterruptedException {
        String uriString = String.format("%s?start=%s&end=%s&currency=%s",
                historicalRateUri,
                DateUtils.convertToUtcIsoString(from),
                DateUtils.convertToUtcIsoString(to),
                currencyCode);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriString))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(httpResponse.body(), HistoricalBpiResponse.class);
    }

    @Override
    public CurrentBpiResponse current(String currencyCode) throws IOException, InterruptedException {
        String uriString = String.format("%s/%s.json", currentRateUri, currencyCode);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriString))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(httpResponse.body(), CurrentBpiResponse.class);
    }

    @Override
    public List<Currency> supportedCurrencies() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(supportedCurrenciesUri))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(httpResponse.body(), new TypeToken<List<Currency>>(){}.getType());
    }

}
