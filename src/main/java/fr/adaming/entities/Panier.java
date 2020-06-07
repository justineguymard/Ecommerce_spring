package fr.adaming.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Panier implements Serializable {

	private Map<Long, LigneCommande> produitsPanier = new HashMap<Long, LigneCommande>();

	public LigneCommande ajoutProduitPanier(Produit produitIn, int quantite) {

		LigneCommande articlePanier = produitsPanier.get(produitIn.getIdProduit());

		if (articlePanier == null) {
			LigneCommande articlePanierNew = new LigneCommande();
			articlePanierNew.setProduit(produitIn);
			articlePanierNew.setQuantite(quantite);
			articlePanierNew.setPrix((produitIn.getPrix() * quantite));
			produitsPanier.put(produitIn.getIdProduit(), articlePanierNew);

		} else {
			articlePanier.setQuantite(articlePanier.getQuantite() + quantite);
			articlePanier.setPrix(articlePanier.getPrix() * (articlePanier.getQuantite() + quantite));
		}

		return articlePanier;

	}
	
	public Collection<LigneCommande> getProduits(){
		return produitsPanier.values();
	}

	public int getSize() {
		return produitsPanier.size();
	}
	
	public double getTotal() {
		double total = 0;
		for(LigneCommande produitPanier:produitsPanier.values()) {
			total +=  produitPanier.getPrix()*produitPanier.getQuantite();
		}
		return total;
	}
	
	public void deleeProduit (Long idProduit) {
		produitsPanier.remove(idProduit);
	}
	
	
	
}
