package com.andyhardy.ppt.controller;

import com.andyhardy.ppt.api.TrackProductRequest;
import com.andyhardy.ppt.service.PriceTrackerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.ScheduledFuture;


@RequiredArgsConstructor
@RestController
public class PriceTrackerController {

    private final PriceTrackerService priceTrackerService;


    @PostMapping("track-product")
    public ResponseEntity<?> trackProduct(@Valid @RequestBody TrackProductRequest trackProductRequest) {
        Optional<ScheduledFuture<?>> optionalScheduledFuture = priceTrackerService.trackProduct(trackProductRequest.getProductUrl().toString(),
                trackProductRequest.getThresholdPrice(),
                trackProductRequest.getNotificationFrequency());

        return optionalScheduledFuture.map(scheduledFuture -> ResponseEntity.ok().build()).orElse(ResponseEntity.badRequest().build());
    }

}
