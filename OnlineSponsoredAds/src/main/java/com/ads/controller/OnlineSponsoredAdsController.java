package com.ads.controller;

import com.ads.model.Campaign;
import com.ads.model.Product;
import com.ads.service.OnlineSponsoredAdsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ads")
public class OnlineSponsoredAdsController {

    private final Logger logger = LoggerFactory.getLogger(OnlineSponsoredAdsController.class);
    private final OnlineSponsoredAdsService onlineSponsoredAdsService;

    @PostMapping("/campaign")
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        try {
            Campaign createdCampaign = onlineSponsoredAdsService.createCampaign(campaign);
            return ResponseEntity.ok(createdCampaign);
        } catch (Exception e) {
            logger.error("error processing campaign {}", campaign, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/serve/ad/{category}")
    public ResponseEntity<Product> serveAd(@PathVariable String category) {
        try{
            Product product = onlineSponsoredAdsService.serveAd(category);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.error("error getting products of category {}", category);
            return ResponseEntity.internalServerError().build();
        }
    }

}
