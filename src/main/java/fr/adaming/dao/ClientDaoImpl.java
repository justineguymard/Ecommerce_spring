package fr.adaming.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;

@Repository
public class ClientDaoImpl implements IClientDao {

	private SessionFactory sf;
	
	@Override
	public Client registerClient(Client clientIn, Commande commandeIn) {
		
		Session s = sf.getCurrentSession();
		
		clientIn.getListeCommandes().add(commandeIn);
		s.save(clientIn);
		 
		 return clientIn;
		
		
	}
	
	
	

}
