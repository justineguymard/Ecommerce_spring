package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;

@ManagedBean(name="panierMB")
@SessionScoped
public class PanierManagedBean implements Serializable{
	
	private List <LigneCommande> listeLignesCommande; 
	private LigneCommande ligneCommande;
	private Client client;
	private Commande commande;
	private Produit produit;
	
	
	public PanierManagedBean() {
		super();
	}
	
	@PostConstruct
	public void init () {
		
		this.listeLignesCommande = new ArrayList<>();
		this.client = new Client ();
		this.commande = new Commande();
		this.produit = new Produit();	
		
	}
	

	public String addProduitPanier (Produit produitIn, int quantite) {
		
		this.ligneCommande.setQuantite(quantite);
		this.ligneCommande.setProduit(produitIn);
		this.ligneCommande.setCommande(this.commande);
		this.commande.setClient(this.client);
		
		System.out.println(produitIn);
		
		System.out.println(quantite);
		
		return "4_userProduitListe";
	}
	
	
	

	public List<LigneCommande> getListeLignesCommande() {
		return listeLignesCommande;
	}

	public void setListeLignesCommande(List<LigneCommande> listeLignesCommande) {
		this.listeLignesCommande = listeLignesCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
