package com.andyhardy.ppt.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Notification {

    private final String productUrl;

    private final Integer price;

    private final Integer currentPrice;

}
