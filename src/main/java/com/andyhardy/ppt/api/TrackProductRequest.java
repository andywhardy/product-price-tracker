package com.andyhardy.ppt.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.net.URL;

@Getter
@Builder
public class TrackProductRequest {

    @Schema(name = "productUrl", example = "http://product-site/abc")
    private final URL productUrl;

    @Schema(name = "thresholdPrice", example = "960")
    private final Integer thresholdPrice;

    @Schema(name = "notificationFrequency", example = "* */5 * ? * *")
    private final String notificationFrequency;

}
