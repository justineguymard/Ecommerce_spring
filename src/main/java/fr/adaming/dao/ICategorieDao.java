package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Categorie;

public interface ICategorieDao {

	public List<Categorie> getAllCategorie(Categorie categorie);

	public int addCategorie(Categorie categorie);

	public int updateCategorie(Categorie categorie);

	public int deleteCategorie(Categorie categorie);

	public Categorie serachCategorieByName(Categorie categorie);

}
