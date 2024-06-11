package com.fotograbados.springv1.persistence.repository.ventas;

import com.fotograbados.springv1.persistence.entities.ventas.ChannelCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelCampaignRepository extends JpaRepository<ChannelCampaign, Long> {
}
