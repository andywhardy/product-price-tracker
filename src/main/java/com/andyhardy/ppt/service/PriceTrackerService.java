package com.andyhardy.ppt.service;

import com.andyhardy.ppt.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor
@Slf4j
@Service
public class PriceTrackerService {

    private final NotificationService notificationService;

    private final TaskScheduler taskScheduler;

    private final PriceSimulationService priceSimulationService;

    public Optional<ScheduledFuture<?>> trackProduct(final String productUrl, final Integer thresholdPrice, final String notificationFrequency) {
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(() -> {
            log.info("Checking price: productUrl={} thresholdPrice={}", productUrl, thresholdPrice);
            Integer currentPrice = priceSimulationService.currentPrice(productUrl);

            if (isPriceNotification(currentPrice, thresholdPrice)) {
                Notification notification = notificationService.notifyClient(productUrl, thresholdPrice, currentPrice);
                log.info("Sending notification: productUrl={} has dropped below price {} - (Current price={})", notification.getProductUrl(), notification.getPrice(), notification.getCurrentPrice());
            }
        }, new CronTrigger(notificationFrequency));

        return Optional.of(scheduledFuture);
    }

    public boolean isPriceNotification(final Integer currentPrice, final Integer thresholdPrice) {
        return currentPrice < thresholdPrice;
    }

}
