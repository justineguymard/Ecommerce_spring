package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	@Autowired
	private SessionFactory sf;
	
	@Transactional(readOnly = true)

	@Override
	public List<Produit> getAllProduit() {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		// construire la requete HQL
		String req = "FROM Produit";

		// creation d'un objet de type Query
		Query query = s.createQuery(req);

		// envoyer la requete et recuperer la liste
		return query.list();
	}

	@Override
	public int addProduit(Produit produit) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.save(produit);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateProduit(Produit produit) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			Produit produitModif = s.get(Produit.class, produit.getIdProduit());

			produitModif.setDesignation(produit.getDesignation());
			produitModif.setDescription(produit.getDescription());
			produitModif.setPrix(produit.getPrix());
			produitModif.setQuantite(produit.getQuantite());
			produitModif.setSelectionne(produit.isSelectionne());
			produitModif.setPhoto(produit.getPhoto());

			s.update(produitModif);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteProduit(Produit produit) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.delete(produit);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Produit> searchProduitByCategorie(Categorie categorie) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {
			// ecrire la requete HQL
			String req = "FROM Produit p WHERE p.categorie.nomCategorie=:pNomCat";

			// recup due l'objet Query
			Query query = s.createQuery(req);

			// passage des params
			query.setParameter("pNomCat", categorie.getNomCategorie());

			return query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Produit> searchProduitByName(String motCle) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			String req = "FROM Produit p WHERE p.designation like :sf";

			Query query = s.createQuery(req);

			query.setString("sf", '%'+motCle+'%');

			return query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
