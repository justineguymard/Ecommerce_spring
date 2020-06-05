package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@Service("produitService")
@Transactional
public class ProduitServiceImpl implements IProduitService {

	// TRansformation de l'association UML en JAVA
	@Autowired // injectiojn de dependance
	private IProduitDao produitDao;	
		
	// Le setter pour l'injection
	public void setProduitDao(IProduitDao produitDao) {
		this.produitDao = produitDao;
	}

	@Override
	public List<Produit> getAllProduit() {
		// appel de la méthode dao
		return produitDao.getAllProduit();
	}

	@Override
	public int addProduit(Produit produit, Categorie categorie) {
		
		produit.setCategorie(categorie);
		
		return produitDao.addProduit(produit);
	}

	@Override
	public int updateProduit(Produit produit) {
		// appel de la méthode dao
		return produitDao.updateProduit(produit);
	}

	@Override
	public int deleteProduit(Produit produit) {
		// appel de la méthode dao
		return produitDao.deleteProduit(produit);
	}

	@Override
	public List<Produit> searchProduitByCategorie(Categorie categorie) {
		
		// appel de la méthode dao
		return produitDao.searchProduitByCategorie(categorie);
	}

	@Override
	public List<Produit> searchProduitByName(String motCle) {
		
		// appel de la méthode dao
		return produitDao.searchProduitByName(motCle);
	}

}
