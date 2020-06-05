package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.entities.Categorie;

@Service("categorieService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {

	// TRansformation de l'association UML en JAVA
	@Autowired // injectiojn de dependance
	private ICategorieDao categorieDao;

	// Le setter pour l'injection
	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// appel de la méthode dao
		return categorieDao.getAllCategorie();
	}

	@Override
	public int addCategorie(Categorie categorie) {
		// appel de la méthode dao
		return categorieDao.addCategorie(categorie);
	}

	@Override
	public int updateCategorie(Categorie categorie) {
		// appel de la méthode dao
		return categorieDao.updateCategorie(categorie);
	}

	@Override
	public int deleteCategorie(Categorie categorie) {
		// appel de la méthode dao
		return categorieDao.deleteCategorie(categorie);
	}

	@Override
	public Categorie searchCategorieByName(Categorie categorie) {
		// appel de la méthode dao
		return categorieDao.searchCategorieByName(categorie);
	}

}
