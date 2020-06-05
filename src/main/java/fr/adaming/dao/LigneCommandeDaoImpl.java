package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@Autowired
	private SessionFactory sf;

	@Transactional(readOnly = true)

	@Override
	public List<LigneCommande> getAllLigneCommande() {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		// construire la requete HQL
		String req = "FROM LigneCommande";

		// creation d'un objet de type Query
		Query query = s.createQuery(req);

		// envoyer la requete et recuperer la liste
		return query.list();
	}

	@Override
	public int addLigneCommande(LigneCommande ligneCommande) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.save(ligneCommande);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateLigneCommande(LigneCommande ligneCommande) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			LigneCommande ligneCommandeModif = s.get(LigneCommande.class, ligneCommande.getId());

			ligneCommandeModif.setQuantite(ligneCommande.getQuantite());
			ligneCommandeModif.setPrix(ligneCommande.getPrix());
			ligneCommandeModif.setProduit(ligneCommande.getProduit());
			ligneCommandeModif.setCommande(ligneCommande.getCommande());

			s.update(ligneCommandeModif);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteLigneCommande(LigneCommande ligneCommande) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.delete(ligneCommande);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
