package fr.adaming.dao;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LigneCommande ajoutProduitPanier(Produit produitIn, int quantite) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public List<Produit> produitsSelectionnes() {
	// Query req = em.createQuery("select p from Produit p WHere p.selectionne =
	// true");
	//
	// return req.getResultList();
	// }
	//
	// @Override
	// public Produit GetProduit(Long idProduit) {
	//
	// return em.find(Produit.class, idProduit);
	// }
	//
	// @Override
	// public void supprimerProduit(Long idProduit) {
	// Produit p = this.GetProduit(idProduit);
	// em.remove(p);
	//
	//
	// }
	//
	// @Override
	// public Commande enrCommande(Panier panierIn, Client clientIn) {
	// em.persist(clientIn);
	// Commande cmd = new Commande();
	// cmd.setClient(clientIn);
	// //cmd.setListeLignesCommandes(panierIn.getProduits());
	// em.persist(cmd);
	// return cmd;
	// }
	//
	// @Override
	// public LigneCommande ajoutProduitPanier(Produit produitIn, int quantite) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
