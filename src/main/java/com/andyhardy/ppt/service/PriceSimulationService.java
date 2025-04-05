package com.andyhardy.ppt.service;

import com.andyhardy.ppt.model.PriceData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class PriceSimulationService {

    private static final long PRICE_REDUCTION_DELAY = 60000L; // 1 minute

    @Value("classpath:price-data.json")
    private Resource priceDataResource;

    private PriceData priceData;

    private final Map<String, Integer> currentPrices = new ConcurrentHashMap<>();

    @SneakyThrows
    @PostConstruct
    public void postConstruct() {
        ObjectMapper objectMapper = new ObjectMapper();
        priceData = objectMapper.readValue(priceDataResource.getInputStream(), PriceData.class);

        if(currentPrices.isEmpty()) {
            priceData.getData().forEach(productPrice -> {
                currentPrices.put(productPrice.getProductUrl(), productPrice.getPriceRange().getStartPrice());
            });
        }
    }

    @Scheduled(fixedDelay=PRICE_REDUCTION_DELAY)
    public void reducePrice() {
        priceData.getData().forEach(productPrice -> {
            Integer newPrice = currentPrices.get(productPrice.getProductUrl()) - 10;

            if(newPrice <= productPrice.getPriceRange().getEndPrice()) {
                log.info("Resetting price simulation: productUrl={} newPrice={}", productPrice.getProductUrl(), productPrice.getPriceRange().getStartPrice());

                currentPrices.put(productPrice.getProductUrl(), productPrice.getPriceRange().getEndPrice());
            } else {
                log.info("Reducing price: productUrl={} newPrice={}", productPrice.getProductUrl(), newPrice);

                currentPrices.put(productPrice.getProductUrl(), newPrice);
            }
        });
    }

    public Integer currentPrice(String productUrl) {
        return currentPrices.get(productUrl);
    }

}
