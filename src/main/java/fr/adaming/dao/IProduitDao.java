package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Produit;

public interface IProduitDao {
	
	public List<Produit> getAllProduit(Produit produit);
	
	public int addProduit(Produit produit);
	
	public int updateProduit(Produit produit);
	
	public int deleteProduit(Produit produit);
	
	public Produit serachCategorieByName(Produit produit);

}
