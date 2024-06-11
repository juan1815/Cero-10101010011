package com.fotograbados.springv1.domain.service.ventas;

import com.fotograbados.springv1.persistence.entities.ventas.ChannelCampaign;
import com.fotograbados.springv1.persistence.repository.ventas.ChannelCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements ICampaignService{
    @Autowired
    private ChannelCampaignRepository campaignRepository;
    @Override
    public ChannelCampaign save(ChannelCampaign channelCampaign) {
        return campaignRepository.save(channelCampaign);
    }

    @Override
    public Optional<ChannelCampaign> get(Long idCampañas) {
        return campaignRepository.findById(idCampañas);
    }

    @Override
    public void update(ChannelCampaign channelCampaign) {
        campaignRepository.save(channelCampaign);
    }

    @Override
    public void delete(Long idCampañas) {
        campaignRepository.findById(idCampañas);
    }

    @Override
    public List<ChannelCampaign> findAll() {
        return campaignRepository.findAll();
    }
}
