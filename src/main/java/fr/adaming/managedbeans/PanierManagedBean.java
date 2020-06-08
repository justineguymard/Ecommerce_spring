package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@ManagedBean(name = "panierMB")
@SessionScoped
public class PanierManagedBean implements Serializable {

	private Map<Long, LigneCommande> mapProduitsPanier = new HashMap<Long, LigneCommande>();
	private Collection<LigneCommande> listeLignesCommande;
	private LigneCommande ligneCommande;
	private Client client;
	private Commande commande;
	private Produit produit;
	private Panier panier;
	private List<Produit> listeProduits;

	@ManagedProperty(value = "#{panierService}")
	private IPanierService panierService;

	@ManagedProperty(value = "#{produitService}")
	private IProduitService produitService;

	// Constructeur
	public PanierManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.mapProduitsPanier = new HashMap<Long, LigneCommande>();
		this.listeLignesCommande = new ArrayList<LigneCommande>();
		this.client = new Client();
		this.commande = new Commande();
		this.produit = new Produit();
		this.ligneCommande = new LigneCommande();
		this.panier = new Panier();
		this.listeProduits = produitService.getAllProduit();
	}

	// getters et setters
	public Collection<LigneCommande> getListeLignesCommande() {
		return listeLignesCommande;
	}

	public void setListeLignesCommande(Collection<LigneCommande> listeLignesCommande) {
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

	public Map<Long, LigneCommande> getMapProduitsPanier() {
		return mapProduitsPanier;
	}

	public void setMapProduitsPanier(Map<Long, LigneCommande> mapProduitsPanier) {
		this.mapProduitsPanier = mapProduitsPanier;
	}

	// methode metier

	public String ajoutProduitPanier() {

		System.out.println("\n ======= ID Produit :" + this.produit.getIdProduit());
		System.out.println("\n =======  Quantite :" + this.ligneCommande.getQuantite());

		//LigneCommande test = this.ligneCommande;
		int quantite = this.ligneCommande.getQuantite();

		LigneCommande test = this.mapProduitsPanier.get(this.produit.getIdProduit());

		if (test == null) {

			this.produit.setSelectionne(true);
			LigneCommande newProduitAjout = new LigneCommande();
			this.produit=panierService.GetProduit(this.produit.getIdProduit());
			newProduitAjout.setProduit(this.produit);
			System.out.println("\n--------------------- "+this.produit.getDesignation());
			newProduitAjout.setQuantite(quantite);
			newProduitAjout.setPrix(quantite * this.produit.getPrix());
			this.mapProduitsPanier.put(this.produit.getIdProduit(), newProduitAjout);

			this.listeLignesCommande= this.mapProduitsPanier.values();

		}else{
			test.setQuantite(this.ligneCommande.getQuantite() + test.getQuantite());
			test.setPrix(test.getProduit().getPrix()* ( test.getQuantite() ));
			this.mapProduitsPanier.replace(this.produit.getIdProduit(),test);
			this.listeLignesCommande= this.mapProduitsPanier.values();
			
		}

		this.produit.setSelectionne(true);

		System.out.println("yes");

		return "4_userAjoutProduitPanier";
	}

}
