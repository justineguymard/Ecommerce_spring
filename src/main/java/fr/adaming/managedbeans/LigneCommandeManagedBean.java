package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.entities.Admin;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;
import fr.adaming.service.ICommandeService;
<<<<<<< HEAD
import fr.adaming.service.ILigneCommandeService;
=======

import fr.adaming.service.ILigneCommandeService;

>>>>>>> 1bc893afe640b1c3e3d7655e691673c9383a4941
import fr.adaming.service.IProduitService;

@ManagedBean(name = "ligneCommandeMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {

	// DEclaration des attributs du managedBeans qui vont ecapsuler le modele
	// MVC2
	private LigneCommande ligneCommande;
	private List<LigneCommande> listeLigneCommande;
	private Admin admin;
	private Produit produit;
	private List<Produit> listeProduit;
	private Commande commande;
	private List<Commande> listeCommande;
	private boolean indice;

	// Transformation de l'association UML en JAVA
	@ManagedProperty(value = "#{ligneCommandeService}")
	private ILigneCommandeService ligneCommandeService;
	@ManagedProperty(value = "#{produitService}")
	private IProduitService produitService;
	@ManagedProperty(value = "#{commandeService}")
	private ICommandeService commandeService;

	// constructeur
	public LigneCommandeManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {

		// recuperer le prof de la session
		this.admin = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adminSession");

		// recuperer le prof de la session
		this.ligneCommande = new LigneCommande();

		// recuperer la liste lors de l'instiation du ManagedBean
		this.listeLigneCommande = ligneCommandeService.getAllLigneCommande();

		// recuperer la liste lors de l'instiation du ManagedBean
		this.listeProduit = produitService.getAllProduit();

		// instancier une catégorie pour éviter l'erreur target unreachable
		this.produit = new Produit();

		// recuperer la liste lors de l'instiation du ManagedBean
		this.listeCommande = commandeService.getAllCommandes();

		// instancier une catégorie pour éviter l'erreur target unreachable
		this.commande = new Commande();

		this.indice = false;// initialiser l'indice à false pour cacher la table
		// avant la recherche

	}

	// getter et setters
	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public List<Commande> getListeCommande() {
		return listeCommande;
	}

	public void setListeCommande(List<Commande> listeCommande) {
		this.listeCommande = listeCommande;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	// le setter pour l'injection de dependance
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
	}
	
	public void setLigneCommandeService(ILigneCommandeService ligneCommandeService) {
		this.ligneCommandeService = ligneCommandeService;
	}

	// ==========================================================================
	// Les méthodes métier du ManagedBean

	public String addLigneCommande() {
		// Appel de la méthode add de Service(Etudiant)
		int verif = ligneCommandeService.addLigneCommande(this.ligneCommande, this.produit, this.commande);

		if (verif != 0) {
			// Mettre à jour la liste des étudiants du prof
			this.listeLigneCommande = ligneCommandeService.getAllLigneCommande();

			// On est ultra giga méga content et on va dans l'accueil
			return "1_adminConsultation";
		} else {
			return "1_adminAjoutLigneCommande";
		}
	}

	public String updateLigneCommande() {
		// appel de la methode update de service
				int verif = ligneCommandeService.updateLigneCommande(this.ligneCommande);

				if (verif != 0) {
					// Mettre à jour la liste des étudiants du prof
					this.listeLigneCommande = ligneCommandeService.getAllLigneCommande();

					// On est on va dans l'accueil
					return "1_adminConsultation";
				} else {
					// ajouter le message d'erreur dans facesContext
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a echouée"));
					return "1_adminModifLigneCommande";
				}
	}

	public String deleteLigneCommande() {
		int verif = ligneCommandeService.deleteLigneCommande(this.ligneCommande);

		if (verif != 0) {
			// Mettre à jour la liste des étudiants du prof
			this.listeLigneCommande = ligneCommandeService.getAllLigneCommande();

			// On est on va dans l'accueil
			return "1_adminConsultation";
		} else {
			// ajouter le message d'erreur dans facesContext
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a echouée"));
			return "1_adminSupprLigneCommande";
		}
	}

}
