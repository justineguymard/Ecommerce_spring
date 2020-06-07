package fr.adaming.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.Panier;
import fr.adaming.entities.Produit;


public class PanierDaoImpl implements IPanierDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<Produit> produitsSelectionnes() {
		Query req = em.createQuery("select p from Produit p WHere p.selectionne = true");
		
		return req.getResultList();
	}

	@Override
	public Produit GetProduit(Long idProduit) {
		
		return em.find(Produit.class, idProduit);
	}

	@Override
	public void supprimerProduit(Long idProduit) {
		 Produit p = this.GetProduit(idProduit);
		 em.remove(p);
		 
		
	}

	@Override
	public Commande enrCommande(Panier panierIn, Client clientIn) {
		em.persist(clientIn);
		Commande cmd = new Commande();
		cmd.setClient(clientIn);
		cmd.setListeLignesCommandes(panierIn.getProduits());
		em.persist(cmd);
		return cmd;
	}

}
