package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {

	@Autowired
	private SessionFactory sf;

	@Transactional(readOnly = true)

	@Override
	public List<Commande> getAllCommandes() {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		// construire la requete HQL
		String req = "FROM Commande";

		// creation d'un objet de type Query
		Query query = s.createQuery(req);

		// envoyer la requete et recuperer la liste
		return query.list();
	}

	@Override
	public Commande addCommande(Commande commandeIn) {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.save(commandeIn);

			return commandeIn;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Commande updateCommande(Commande commandeIn) {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			Commande commandeUpdated = s.get(Commande.class, commandeIn.getIdCommande());

			commandeUpdated.setDateCommande(commandeIn.getDateCommande());

			s.update(commandeUpdated);

			return commandeUpdated;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Commande deleteCommande(Commande commandeIn) {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.delete(commandeIn);

			return commandeIn;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Commande searchCommandeByID(Commande commandeIn) {
		
		Session s = sf.getCurrentSession();

		try {

			String reqOneHQL = "FROM Produit p WHERE p.idCommande=:pIdCommande";

			Query queryOneHQL = s.createQuery(reqOneHQL);

			queryOneHQL.setParameter("pIdCommance", commandeIn.getIdCommande());

			return (Commande) queryOneHQL.uniqueResult();
			

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Commande> searchCommandeByDate(Commande commandeIn) {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			String reqOneHQL = "FROM Commande c WHERE c.dateCommande=:pDateCommande";

			Query query = s.createQuery(reqOneHQL);

			query.setParameter("pDateCOmmande", commandeIn.getDateCommande());

			return query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
