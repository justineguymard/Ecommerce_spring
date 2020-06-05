package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.LigneCommande;

public interface ILigneCommandeDao {
	
	public List<LigneCommande> getAllLigneCommande();
	
	public int addLigneCommande(LigneCommande ligneCommande);
	
	public int updateLigneCommande(LigneCommande ligneCommande);
	
	public int deleteLigneCommande(LigneCommande ligneCommande);

}
