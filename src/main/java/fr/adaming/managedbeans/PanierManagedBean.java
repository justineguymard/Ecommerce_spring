package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Panier;
import fr.adaming.entities.Produit;
import fr.adaming.service.IPanierService;

@ManagedBean(name="panierMB")
@SessionScoped
public class PanierManagedBean implements Serializable{
	
	private List <LigneCommande> listeLignesCommande; 
	private LigneCommande ligneCommande;
	private Client client;
	private Commande commande;
	private Produit produit;
	private Panier panier;
	
	@ManagedProperty(value="#{panierService}")
	private IPanierService panierService;
	
	
	public PanierManagedBean() {
		super();
	}
	
	@PostConstruct
	public void init () {
		
		this.listeLignesCommande = new ArrayList<>();
		this.client = new Client ();
		this.commande = new Commande();
		this.produit = new Produit();	
		this.ligneCommande = new LigneCommande();
		this.panier = new Panier();
		
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

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public IPanierService getPanierService() {
		return panierService;
	}

	public void setPanierService(IPanierService panierService) {
		this.panierService = panierService;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}
	

	
	public String ajoutProduitPanier () {
		
		System.out.println("\n ======= \n\n"+this.produit.getIdProduit());
		
		this.panier.ajoutProduitPanier(this.produit, this.ligneCommande.getQuantite());
		
		this.produit.setSelectionne(true);
		
		System.out.println("yes");
		
		return "4_userProduitListe";
	}
	
}
