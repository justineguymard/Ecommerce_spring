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
import fr.adaming.service.IProduitService;

@ManagedBean(name="panierMB")
@SessionScoped
public class PanierManagedBean implements Serializable{
	
	private List <LigneCommande> listeLignesCommande; 
	private LigneCommande ligneCommande;
	private Client client;
	private Commande commande;
	private Produit produit;
	private Panier panier;
	private List<Produit> listeProduits;
	
	@ManagedProperty(value="#{panierService}")
	private IPanierService panierService;
	
	@ManagedProperty(value="#{produitService}")
	private IProduitService produitService;
	
	//Constructeur
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
		this.listeProduits = produitService.getAllProduit();
	}
	
	//getters et setters
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
	
	

	
	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	
	
	//methode metier
	
	public String ajoutProduitPanier () {
		
		System.out.println("\n ======= ID Produit :"+this.produit.getIdProduit());
		System.out.println("\n =======  Quantite :"+this.ligneCommande.getQuantite());
		
		this.panier.ajoutProduitPanier(this.produit, this.ligneCommande.getQuantite());
		
		this.produit.setSelectionne(true);
		
		System.out.println("yes");
		
		return "4_userProduitListe";
	}
	
}
