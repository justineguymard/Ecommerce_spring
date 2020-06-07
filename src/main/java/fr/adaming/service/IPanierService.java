package fr.adaming.service;

import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;

public interface IPanierService {
	
	public LigneCommande addProduitPanier (int quantite);

}
