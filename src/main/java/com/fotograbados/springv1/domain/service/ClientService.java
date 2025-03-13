package com.fotograbados.springv1.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fotograbados.springv1.persistence.entities.Client;
import com.fotograbados.springv1.persistence.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    ClientRepository clienteRepository;

    public int saveOrUpdate(Client client){
        int res= 0;
		Client Client = clienteRepository.save(client);
		if(!Client.equals(null)){
			res =1;
		}
		return res;
	}

    public List<Client> findAll() {
		return clienteRepository.findAll();
	}

	public Optional<Client> findById(Long id) {
		return clienteRepository.findById(id);
	}

    //login pending test
}
