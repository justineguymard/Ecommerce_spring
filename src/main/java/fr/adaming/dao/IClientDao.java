package fr.adaming.dao;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;

public interface IClientDao {
	
	public Client registerClient (Client clientIn, Commande commande);
	
	

}
