package com.ads.service;

import com.ads.model.Campaign;
import com.ads.model.CampaignStatus;
import com.ads.model.Product;
import com.ads.repository.CampaignRepository;
import com.ads.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class OnlineSponsoredAdsService {
    private final CampaignRepository campaignRepository;
    private final ProductRepository productRepository;

    private final int CAMPAIGN_MAX_ACTIVE_DAYS = 10;

    @Transactional
    public Campaign createCampaign(Campaign campaign) {
        List<UUID> productIds = campaign.getProducts().stream().filter(Objects::nonNull).map(Product::getSerialNumber).toList();
        List<Product> products = productRepository.findAllById(productIds);
        campaign.setProducts(products);
        campaignRepository.save(campaign);
        return campaign;
    }

    public Product serveAd(String category) {
        Product product = productRepository.getHighestBidProductByCategory(category);
        if (product == null) {
            product = productRepository.getHighestBidProduct();
        }
        return product;
    }

    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void deactivateOutdatedCampaigns() {
        campaignRepository.deactivateCampaigns(CampaignStatus.INACTIVE, LocalDateTime.now().minusDays(CAMPAIGN_MAX_ACTIVE_DAYS));
    }
}
