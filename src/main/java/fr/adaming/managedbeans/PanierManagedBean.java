package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	private String nomClient;
	private String prenomClient;
	private String rueClient;
	private String cpClient;
	private String villeClient;
	private String paysClient;
	private double total;
	private int size;
	
	

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
		this.total = 0.0;
		this.size = 0;
	
	}

	// getters et setters
	public Collection<LigneCommande> getListeLignesCommande() {
		return listeLignesCommande;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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

	
	


	public void setTotal(double total) {
		this.total = total;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getRueClient() {
		return rueClient;
	}

	public void setRueClient(String rueClient) {
		this.rueClient = rueClient;
	}

	public String getCpClient() {
		return cpClient;
	}

	public void setCpClient(String cpClient) {
		this.cpClient = cpClient;
	}

	public String getVilleClient() {
		return villeClient;
	}

	public void setVilleClient(String villeClient) {
		this.villeClient = villeClient;
	}

	public String getPaysClient() {
		return paysClient;
	}

	public void setPaysClient(String paysClient) {
		this.paysClient = paysClient;
	}

	
	

	// methode metier
	
	
	public String ajoutProduitPanier() {

		System.out.println("\n ======= ID Produit :" + this.produit.getIdProduit());
		System.out.println("\n =======  Quantite :" + this.ligneCommande.getQuantite());

		// LigneCommande test = this.ligneCommande;
		int quantite = this.ligneCommande.getQuantite();

		if (quantite == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ajout impossible, veuillez vérifier la quantité choisie."));
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
			//maj de la liste pour affichage du panier
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
		//maj de size
		this.size=getSize();
		//maj de montant
		this.total = getTotal();
		return "cart";
	} // fin de la méthode

	public String enrCommande() {
		//obtention du nom et prenom
		this.client.setNomClient(this.nomClient+" "+this.prenomClient);
		//idem adresse complete
		this.client.setAdresse(this.rueClient+ ", "+this.cpClient+" "+this.villeClient+", "+this.paysClient);
		//verif
		System.out.println(this.client);

		this.panier.setMapProduitsPanier(this.mapProduitsPanier);
		//enregistrement dans la base de données
		panierService.enrCommande(this.panier, this.client);

		System.out.println("taille du panier : " + this.panier.getMapProduitsPanier().size());
		//boucle for pour parcourir la map
		for (Map.Entry mapentry : this.mapProduitsPanier.entrySet()) {
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());

			this.mapProduitsPanier.get(mapentry.getKey()).getProduit().setSelectionne(false);
		}
		this.mapProduitsPanier.clear();
		
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Commande enregistrée."));
		init(); //reset du bean
		return "checkout";

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

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Ce(s) produit(s) ont été retiré(s) du panier."));
		//maj de la taille
		this.size=getSize();
		//maj du montant
		this.total =getTotal();
		return "cart";

	}

	public String clearMap() {

		for (Map.Entry mapentry : this.mapProduitsPanier.entrySet()) {
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());

			this.mapProduitsPanier.get(mapentry.getKey()).getProduit().setSelectionne(false);
		}
		this.mapProduitsPanier.clear();

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Votre panier a été vidé."));
		this.size=getSize();
		this.total = getTotal();
		
		return "cart";
		

	}
	
	public double getTotal () {
		
		this.total =0.0;
		for (Map.Entry mapentry : this.mapProduitsPanier.entrySet()) {
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());

			this.total += this.mapProduitsPanier.get(mapentry.getKey()).getPrix();
			System.out.println("total :"+total);
			
		}
		System.out.println("montant total : "+total);
		return total;
	}

	
	public double getsize () {
		
		this.size = 0;
		for (Map.Entry mapentry : this.mapProduitsPanier.entrySet()) {
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());

			this.size += this.mapProduitsPanier.get(mapentry.getKey()).getQuantite();
			System.out.println("total :"+total);
			
		}

		System.out.println("montant total : "+total);
		return size;
	}


}
