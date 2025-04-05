package com.andyhardy.ppt.service;

import com.andyhardy.ppt.model.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public Notification notifyClient(final String productUrl, final Integer price, Integer currentPrice) {
        return Notification
                .builder()
                .productUrl(productUrl)
                .price(price)
                .currentPrice(currentPrice)
                .build();
    }

}
