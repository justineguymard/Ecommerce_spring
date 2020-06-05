package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IProduitDao {
	
	public List<Produit> getAllProduit();
	
	public int addProduit(Produit produit);
	
	public int updateProduit(Produit produit);
	
	public int deleteProduit(Produit produit);
	
	public List<Produit> searchProduitByCategorie(Categorie categorie);
	
	public List<Produit> searchProduitByName(String motcle);

}
