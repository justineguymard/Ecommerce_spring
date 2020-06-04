package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Produit;

public interface IProduitDao {
	
	public List<Produit> getAllProduit();
	
	public int addProduit(Produit produit);
	
	public int updateProduit(Produit produit);
	
	public int deleteProduit(Produit produit);
	
	public List<Produit> searchProduitByCategorie(Produit produit);
	
	public Produit searchProduitByName(Produit produit);

}
