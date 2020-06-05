package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.entities.Client;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;
	
	@Override
	public List<Client> getAllClients() {
		
		return clientDao.getAllClients();
	}

	@Override
	public Client addClient(Client clientIn) {
	
		return clientDao.addClient(clientIn);
	}

	@Override
	public Client updateClient(Client clientIn) {
	
		return clientDao.updateClient(clientIn);
	}

	@Override
	public Client deleteClient(Client clientIn) {
		
		return clientDao.deleteClient(clientIn);
	}

	@Override
	public Client searchClientByID(Client clientIn) {
		
		return clientDao.searchClientByID(clientIn);
	}

	@Override
	public List<Client> searchClientByNom(Client clientIn) {
		
		return clientDao.searchClientByNom(clientIn);
	}
	
	

}
