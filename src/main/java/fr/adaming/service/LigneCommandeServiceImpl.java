package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;

@Service("ligneCommandeService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeService {

	// TRansformation de l'association UML en JAVA
	@Autowired // injectiojn de dependance
	private ILigneCommandeDao lgDao;

	// Le setter pour l'injection
	public void setLgDao(ILigneCommandeDao lgDao) {
		this.lgDao = lgDao;
	}

	@Override
	public List<LigneCommande> getAllLigneCommande() {
		// appel de la méthode dao
		return lgDao.getAllLigneCommande();
	}

	@Override
	public int addLigneCommande(LigneCommande ligneCommande, Produit produit, Commande commande) {
		
		ligneCommande.setProduit(produit);
		ligneCommande.setCommande(commande);
		
		return lgDao.addLigneCommande(ligneCommande);
	}

	@Override
	public int updateLigneCommande(LigneCommande ligneCommande) {
		// appel de la méthode dao
		return lgDao.updateLigneCommande(ligneCommande);
	}

	@Override
	public int deleteLigneCommande(LigneCommande ligneCommande) {
		// appel de la méthode dao
		return lgDao.deleteLigneCommande(ligneCommande);
	}

}
