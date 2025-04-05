package com.andyhardy.ppt.controller;


import com.andyhardy.ppt.api.TrackProductRequest;
import com.andyhardy.ppt.service.PriceTrackerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PriceTrackerControllerTest {

    private PriceTrackerService priceTrackerService;

    private PriceTrackerController priceTrackerController;

    @BeforeEach
    public void setUp() {
        priceTrackerService = mock(PriceTrackerService.class);

        priceTrackerController = new PriceTrackerController(priceTrackerService);
    }

    @SneakyThrows
    @Test
    public void trackProduct_GivenValidRequest_ReturnsOK() {
        String productUrl = "http://product-site/abc";
        Integer thresholdPrice = 1;
        String notificationFrequency = "0 */5 * ? * *";

        TrackProductRequest trackProductRequest = TrackProductRequest
                .builder()
                .productUrl(new URL(productUrl))
                .thresholdPrice(thresholdPrice)
                .notificationFrequency(notificationFrequency)
                .build();

        ScheduledFuture<?> scheduledFuture = mock(ScheduledFuture.class);

        when(priceTrackerService.trackProduct(productUrl, thresholdPrice, notificationFrequency)).thenReturn(Optional.of(scheduledFuture));
        ResponseEntity<?> actualResult = priceTrackerController.trackProduct(trackProductRequest);

        assertThat(actualResult.getStatusCode(), is(HttpStatus.OK));
    }

    @SneakyThrows
    @Test
    public void trackProduct_GivenValidRequest_ReturnsBadRequest() {
        String productUrl = "http://product-site/abc";
        Integer thresholdPrice = 1;
        String notificationFrequency = "0 */5 * ? * *";

        TrackProductRequest trackProductRequest = TrackProductRequest
                .builder()
                .productUrl(new URL(productUrl))
                .thresholdPrice(thresholdPrice)
                .notificationFrequency(notificationFrequency)
                .build();


        when(priceTrackerService.trackProduct(productUrl, thresholdPrice, notificationFrequency)).thenReturn(Optional.empty());
        ResponseEntity<?> actualResult = priceTrackerController.trackProduct(trackProductRequest);

        assertThat(actualResult.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

}