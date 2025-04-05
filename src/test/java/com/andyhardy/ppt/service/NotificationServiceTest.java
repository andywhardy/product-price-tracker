package com.andyhardy.ppt.service;

import com.andyhardy.ppt.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class NotificationServiceTest {

    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        notificationService = new NotificationService();
    }

    @Test
    public void notifyClient_GivenValidParameters_ReturnsNotification() {
        String productUrl = "http://product-site/abc";
        Integer price = 1;
        Integer currentPrice = 1;

        Notification actualResult = notificationService.notifyClient(productUrl, price, currentPrice);

        assertThat(actualResult.getProductUrl(), is("http://product-site/abc"));
        assertThat(actualResult.getPrice(), is(1));
        assertThat(actualResult.getCurrentPrice(), is(1));
    }

}