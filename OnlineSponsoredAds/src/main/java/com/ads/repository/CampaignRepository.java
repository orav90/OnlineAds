package com.ads.repository;

import com.ads.model.Campaign;
import com.ads.model.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, UUID> {

    @Modifying
    @Query("UPDATE Campaign c SET c.status = :status WHERE c.status = 'ACTIVE' AND c.startDate <= :endDate")
    void deactivateCampaigns(@Param("status") CampaignStatus status, @Param("endDate") LocalDateTime endDate);

}
