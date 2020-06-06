package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;

public interface ICommandeService {
	
	
	public List<Commande> getAllCommandes ();
	
	public Commande addCommande (Commande commandeIn, Client clientIn);
	
	public Commande updateCommande (Commande commandeIn);
	
	public Commande deleteCommande (Commande commandeIn);
	
	public Commande searchCommandeByID (Commande commandeIn);
	
	public List<Commande> searchCommandeByDate (Commande commandeIn);

}
