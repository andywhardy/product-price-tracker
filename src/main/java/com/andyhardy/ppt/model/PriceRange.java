package com.andyhardy.ppt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class PriceRange {

    private final Integer startPrice;

    private final Integer endPrice;

}
