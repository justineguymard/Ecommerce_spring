package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Categorie;

public interface ICategorieService {

	public List<Categorie> getAllCategorie();

	public int addCategorie(Categorie categorie);

	public int updateCategorie(Categorie categorie);

	public int deleteCategorie(Categorie categorie);

	public Categorie searchCategorieByName(Categorie categorie);

}
