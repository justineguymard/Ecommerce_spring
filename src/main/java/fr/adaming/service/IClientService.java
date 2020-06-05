package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Client;

public interface IClientService {
	
	public List<Client> getAllClients ();
	
	public Client addClient (Client clientIn);
	
	public Client updateClient (Client clientIn);
	
	public Client deleteClient (Client clientIn);
	
	public Client searchClientByID (Client clientIn);
	
	public List<Client> searchClientByNom (Client clientIn);

}
