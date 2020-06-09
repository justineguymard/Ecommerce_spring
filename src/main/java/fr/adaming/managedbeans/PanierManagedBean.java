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

		// LigneCommande test = this.ligneCommande;
		int quantite = this.ligneCommande.getQuantite();

		if (quantite == 0) {
			return "shop";
		}
		// anouvelle LC pour stocker la valeur
		LigneCommande test = this.mapProduitsPanier.get(this.produit.getIdProduit());

		// si le produit n'a pas été commandé
		if (test == null) {

			this.produit.setSelectionne(true);
			LigneCommande newProduitAjout = new LigneCommande();
			this.produit = panierService.GetProduit(this.produit.getIdProduit());
			newProduitAjout.setProduit(this.produit);

			System.out.println(this.produit);

			newProduitAjout.setQuantite(quantite);
			newProduitAjout.setPrix(quantite * this.produit.getPrix());
			this.mapProduitsPanier.put(this.produit.getIdProduit(), newProduitAjout);

			this.listeLignesCommande = this.mapProduitsPanier.values();

		} else {
			// si le produit a deja été commande : ajustemeent de la quantite et du prix
			// total
			// quantite
			test.setQuantite(this.ligneCommande.getQuantite() + test.getQuantite());
			// prix
			test.setPrix(test.getProduit().getPrix() * (test.getQuantite()));
			// remplacer par la nouvelle ligne de commande
			this.mapProduitsPanier.replace(this.produit.getIdProduit(), test);
			// verification du produit de la ligne de commande
			System.out.println(this.produit);

			this.listeLignesCommande = this.mapProduitsPanier.values();

		}
		// actualisation de la map
		this.listeLignesCommande = this.mapProduitsPanier.values();

		// verif de la map
		System.out.println("Boucle for:");
		for (Map.Entry mapentry : this.mapProduitsPanier.entrySet()) {
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
			// verif de l'id produit
			// ajustement de de l'id produit dans la ligne de commande par rapport à la clé
			// enregistrée dans la map
			// parce qu'il gardait l'id du produit précédent et modfifiait les id des
			// produits dans la map
			// la clé elle, est toujours celle de l'id du produit car c'est la valeur clé.
			this.mapProduitsPanier.get(mapentry.getKey())
					.setProduit(this.panierService.GetProduit((long) mapentry.getKey().hashCode()));
			System.out.println(this.mapProduitsPanier.get(mapentry.getKey()).getProduit().getIdProduit());
		}

		return "4_userPanierListe";
	} // fin de la méthode

	public String enrCommande() {

		System.out.println(this.client);

		this.panier.setMapProduitsPanier(this.mapProduitsPanier);

		panierService.enrCommande(this.panier, this.client);

		System.out.println("taille du panier : " + this.panier.getMapProduitsPanier().size());

		return "4_userEnregistrement";

	}

	public String deleteLC() {

		// verification
		System.out.println("Suppr cette ligne :" + this.ligneCommande);
		System.out.println("Suppr ce produit : " + this.ligneCommande.getProduit().getIdProduit());
		System.out.println("Suppr ce produit : " + this.ligneCommande.getProduit());
		// suppresion de la LC dans la map
		this.ligneCommande.getProduit().setSelectionne(false);
		
		System.out.println("Suppr ce produit : " + this.ligneCommande.getProduit());
		this.mapProduitsPanier.remove(this.ligneCommande.getProduit().getIdProduit());

		
		
		return "4_userAjoutProduitPanier";

	}

	public String clearMap() {

		for (Map.Entry mapentry : this.mapProduitsPanier.entrySet()) {
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());

			this.mapProduitsPanier.get(mapentry.getKey()).getProduit().setSelectionne(false);
		}
		this.mapProduitsPanier.clear();

		return "4_userPanierListe";

	}


}
