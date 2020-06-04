package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IProduitService {

	public List<Produit> getAllProduit();

	public int addProduit(Produit produit);

	public int updateProduit(Produit produit);

	public int deleteProduit(Produit produit);

	public List<Produit> searchProduitByCategorie(Produit produit, Categorie categorie);

	public Produit searchProduitByName(Produit produit);

}
