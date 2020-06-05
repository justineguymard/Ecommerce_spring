package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired
	private SessionFactory sf;
	
	@Transactional(readOnly = true)

	@Override
	public List<Categorie> getAllCategorie() {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		// construire la requete HQL
		String req = "FROM Categorie";

		// creation d'un objet de type Query
		Query query = s.createQuery(req);

		// envoyer la requete et recuperer la liste
		return query.list();
	}

	@Override
	public int addCategorie(Categorie categorie) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.save(categorie);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateCategorie(Categorie categorie) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			Categorie categorieModif = s.get(Categorie.class, categorie.getIdCategorie());

			categorieModif.setNomCategorie(categorie.getNomCategorie());
			categorieModif.setPhoto(categorie.getPhoto());
			categorieModif.setDescription(categorie.getDescription());

			s.update(categorieModif);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteCategorie(Categorie categorie) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.delete(categorie);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Categorie searchCategorieByName(Categorie categorie) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			String reqOneHQL = "FROM Categorie c WHERE c.nomCategorie=:pNomCat";

			Query queryOneHQL = s.createQuery(reqOneHQL);

			queryOneHQL.setParameter("pNomCat", categorie.getNomCategorie());

			return (Categorie) queryOneHQL.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
