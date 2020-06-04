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
	public int addProduit(Produit produit) {
		
		Produit produitOut = produitDao.searchProduitByName(produit);

		if (produitOut == null) {
			// appel de la méthode dao
			return produitDao.updateProduit(produit);
		}
		return 0;
	}

	@Override
	public int updateProduit(Produit produit) {
		// appel de la méthode dao
		return produitDao.updateProduit(produit);
	}

	@Override
	public int deleteProduit(Produit produit) {
		
		Produit produitOut = produitDao.searchProduitByName(produit);

		if (produitOut != null) {
			// appel de la méthode dao
			return produitDao.updateProduit(produit);
		}
		return 0;
	}

	@Override
	public List<Produit> searchProduitByCategorie(Produit produit, Categorie categorie) {
		
		// lier le produit avec la catégorie
		produit.setCategorie(categorie);
		
		// appel de la méthode dao
		return produitDao.searchProduitByCategorie(produit);
	}

	@Override
	public Produit searchProduitByName(Produit produit) {
		
		// appel de la méthode dao
		return produitDao.searchProduitByName(produit);
	}

}
