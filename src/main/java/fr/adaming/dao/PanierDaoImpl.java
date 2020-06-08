package fr.adaming.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Panier;
import fr.adaming.entities.Produit;

@Repository
public class PanierDaoImpl implements IPanierDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public List<Produit> produitsSelectionnes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit GetProduit(Long idProduit) {
		Session s = sf.getCurrentSession();
		return s.get(Produit.class, idProduit);
	}

	@Override
	public void supprimerProduit(Long idProduit) {
		// TODO Auto-generated method stub

	}

	 @Override
	 public Commande enrCommande(Panier panierIn, Client clientIn) {
		 //session
		 Session s = sf.getCurrentSession();
		 //enregistement du client qui apsse commande
		 s.persist(clientIn);
		 //creation d'une nouvelle commande
		 Commande cmd = new Commande();
		 
		 //association de la commande au client
		 cmd.setClient(clientIn);
		 
		 //date de la commande
		 Date date = new Date();
		 
		 //stockage de la date 
		 cmd.setDateCommande(date);
		 
		 //verif console
		 System.out.println(clientIn);
		 
		 // liaison des lignes de commande à la meme commande crée au dessus
		 panierIn.getProduits().forEach(x -> x.setCommande(cmd));
		 //panierIn.getProduits().forEach(x -> s.persist(x));
	
		 
		 //boucle for pour parcourir la map
		 for (Map.Entry mapentry : panierIn.getMapProduitsPanier().entrySet()) {
			 System.out.println("\n ==== clé : "+mapentry.getKey()+" | valeur : "+mapentry.getValue());
			 
			 // ligne de commande temporaire pour stocker la ligne parcouru, idem produit
			 LigneCommande lcTemp = new LigneCommande();
			 Produit prodTemp = new Produit();
			 
			 //stockage de la ligne
			 lcTemp = (LigneCommande) mapentry.getValue();
			 
			 //stockage du produit dans la ligne associée
			 int temp = mapentry.getKey().hashCode();
			 lcTemp.setProduit(prodTemp = GetProduit((long) temp));
			 
			 // System.out.println(" ==== Prod ID : "+temp);
			 //enregistrement de la ligne et son produit associé dans la BD
			 s.persist(lcTemp);
			// System.out.println("test lc : "+lcTemp+"Produit : "+lcTemp.getProduit().getIdProduit()+"\n ====");
			 
		 }
		 
		
		//enregistrement de la commande dans la BD
	 	s.persist(cmd);
		
	 	return cmd;
	 }

	@Override
	public LigneCommande ajoutProduitPanier(Produit produitIn, int quantite) {
		// TODO Auto-generated method stub
		return null;
	}


}
