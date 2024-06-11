package com.fotograbados.springv1.domain.service.ventas;


import com.fotograbados.springv1.persistence.entities.ventas.ChannelCampaign;

import java.util.List;
import java.util.Optional;

public interface ICampaignService {
    public ChannelCampaign save(ChannelCampaign channelCampaign);
    Optional<ChannelCampaign> get(Long idCampañas);
    public void update(ChannelCampaign channelCampaign);
    public void delete(Long idCampañas);
    public List<ChannelCampaign> findAll();
}
