package com.andyhardy.ppt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Getter
@Builder
@Jacksonized
public class PriceData {

    private final Set<ProductPrice> data;

}
