package ru.itis.auctionmarketplace.service.impl;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.auctionmarketplace.service.CurrencyConverterService;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record Currency(String baseCode, String targetCode, BigDecimal conversionRate) {};

    @Value("${api-key}")
    private String key;
    private final RestTemplate restTemplate = new RestTemplate();
    private BigDecimal result = BigDecimal.valueOf(0);

    @Override
    public BigDecimal convertToRub(BigDecimal amount, String currency) {

        String url = "https://v6.exchangerate-api.com/v6/%s/pair/%s/RUB".formatted(key, currency);

        if("RUB".equals(currency)) {
            return amount;
        }

        ResponseEntity<Currency> response = restTemplate.getForEntity(url, Currency.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            BigDecimal rate = response.getBody().conversionRate();
            result = amount.multiply(rate);
        }
        return result;
    }
}
