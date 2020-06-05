package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Client;

@Repository
public class ClientDaoImpl implements IClientDao {

	@Autowired
	private SessionFactory sf;
	
	@Transactional(readOnly = true)
	
	@Override
	public List<Client> getAllClients() {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		// construire la requete HQL
		String req = "FROM Client";

		// creation d'un objet de type Query
		Query query = s.createQuery(req);

		// envoyer la requete et recuperer la liste
		return query.list();
	}

	@Override
	public Client addClient(Client clientIn) {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.save(clientIn);

			return clientIn;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Client updateClient(Client clientIn) {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			Client clientUpdated = s.get(Client.class, clientIn.getIdClient());

			clientUpdated.setNomClient(clientIn.getNomClient());
			clientUpdated.setAdresse(clientIn.getAdresse());
			clientUpdated.setEmail(clientIn.getEmail());
			clientUpdated.setTel(clientIn.getTel());

			s.update(clientUpdated);

			return clientUpdated;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Client deleteClient(Client clientIn) {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			s.delete(clientIn);

			return clientIn;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public Client searchClientByID(Client clientIn) {
		
		Session s = sf.getCurrentSession();

		try {

			String reqOneHQL = "FROM Client c WHERE c.idClient=:pIdClient";

			Query queryOneHQL = s.createQuery(reqOneHQL);

			queryOneHQL.setParameter("pIdClient", clientIn.getIdClient());

			return (Client) queryOneHQL.uniqueResult();
			

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Client> searchClientByNom(Client clientIn) {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		try {

			String reqOneHQL = "FROM Client c WHERE c.nomClient=:pNomClient";

			Query query = s.createQuery(reqOneHQL);

			query.setParameter("pNomClient", clientIn.getNomClient());

			return query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
