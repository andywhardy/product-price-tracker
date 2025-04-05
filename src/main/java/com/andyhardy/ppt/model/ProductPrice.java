package com.andyhardy.ppt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ProductPrice {

    private final String productUrl;

    private final PriceRange priceRange;

}
