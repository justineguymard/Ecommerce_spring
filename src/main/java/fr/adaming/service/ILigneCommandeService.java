package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;

public interface ILigneCommandeService {

	public List<LigneCommande> getAllLigneCommande();

	public int addLigneCommande(LigneCommande ligneCommande, Produit produit, Commande commande);

	public int updateLigneCommande(LigneCommande ligneCommande);

	public int deleteLigneCommande(LigneCommande ligneCommande);

}
