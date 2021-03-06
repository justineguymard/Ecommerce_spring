package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Panier;
import fr.adaming.entities.Produit;

public interface IPanierService {
	
	public List<Produit> produitsSelectionnes();
	
	public Produit GetProduit (Long idProduit);
	
	public void supprimerProduit (Long idProduit);
	
	public Commande enrCommande (Panier panierIn, Client clientIn);

}
