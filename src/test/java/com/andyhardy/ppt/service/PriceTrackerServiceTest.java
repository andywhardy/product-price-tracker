package com.andyhardy.ppt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

class PriceTrackerServiceTest {

    private NotificationService notificationService;

    private TaskScheduler taskScheduler;

    private PriceSimulationService priceSimulationService;

    private PriceTrackerService priceTrackerService;

    @BeforeEach
    public void setUp() {
        notificationService = mock(NotificationService.class);
        taskScheduler = mock(ThreadPoolTaskScheduler.class);
        priceSimulationService = mock(PriceSimulationService.class);

        priceTrackerService = new PriceTrackerService(notificationService, taskScheduler, priceSimulationService);
    }

    @Test
    public void isPriceNotification_GivenPriceAboveThreshold_ReturnsFalse() {
        Integer currentPrice = 10;
        Integer thresholdPrice = 5;

        boolean actualResult = priceTrackerService.isPriceNotification(currentPrice, thresholdPrice);

        assertThat(actualResult, is(false));
    }

    @Test
    public void isPriceNotification_GivenPriceBelowThreshold_ReturnsTrue() {
        Integer currentPrice = 5;
        Integer thresholdPrice = 10;

        boolean actualResult = priceTrackerService.isPriceNotification(currentPrice, thresholdPrice);

        assertThat(actualResult, is(true));
    }

}