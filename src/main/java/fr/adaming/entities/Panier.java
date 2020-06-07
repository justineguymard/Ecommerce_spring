package fr.adaming.entities;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedProperty;



public class Panier implements Serializable {
	
	//attributs
	private List<LigneCommande> listeLigneCommande;
	private Commande commande;
	private Produit produit;
	
	@ManagedProperty(value="#panierService")
	private IPanierService panierService;
	
	
	//Getter et setters
	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}
	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
	
	

}
