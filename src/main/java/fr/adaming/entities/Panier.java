package fr.adaming.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Panier implements Serializable {

	private Map<Long, LigneCommande> mapProduitsPanier = new HashMap<Long, LigneCommande>();

	public LigneCommande ajoutProduitPanier(Produit produitIn, int quantite) {

		LigneCommande articlePanier = mapProduitsPanier.get(produitIn.getIdProduit());

		if (articlePanier == null) {
			LigneCommande articlePanierNew = new LigneCommande();
			produitIn.setSelectionne(true);
			articlePanierNew.setProduit(produitIn);
			articlePanierNew.setQuantite(quantite);
			articlePanierNew.setPrix((produitIn.getPrix() * quantite));
			mapProduitsPanier.put(produitIn.getIdProduit(), articlePanierNew);

		} else {
			articlePanier.setQuantite(articlePanier.getQuantite() + quantite);
			articlePanier.setPrix(articlePanier.getPrix() * (articlePanier.getQuantite() + quantite));
		}

		return articlePanier;

	}
	
	public Collection <LigneCommande> getProduits(){
		return mapProduitsPanier.values();
	}

	public int getSize() {
		return mapProduitsPanier.size();
	}
	
	public double getTotal() {
		double total = 0;
		for(LigneCommande produitPanier:mapProduitsPanier.values()) {
			total +=  produitPanier.getPrix()*produitPanier.getQuantite();
		}
		return total;
	}
	
	public void deleteProduit (Long idProduit) {
		mapProduitsPanier.remove(idProduit);
	}


	
	
}
