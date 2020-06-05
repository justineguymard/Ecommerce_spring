package fr.adaming.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.entities.Admin;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "produitBean")
@RequestScoped
public class ProduitManagedBean {

	// DEclaration des attributs du managedBeans qui vont ecapsuler le modele
	// MVC2
	private Admin admin;
	private Categorie categorie;
	private List<Categorie> listeCategorie;
	private Produit produit;
	private List<Produit> listeProduit;
	private boolean indice;

	// Transformation de l'association UML en JAVA
	@ManagedProperty(value = "#{categorieService}")
	private ICategorieService categorieService;
	@ManagedProperty(value = "#{produitService}")
	private IProduitService produitService;

	// constructeur
	public ProduitManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {

		// recuperer le prof de la session
		this.admin = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adminSession");

		// recuperer la liste lors de l'instiation du ManagedBean
		this.listeCategorie = categorieService.getAllCategorie();

		// instancier une catégorie pour éviter l'erreur target unreachable
		this.categorie = new Categorie();

		// recuperer la liste lors de l'instiation du ManagedBean
		this.listeProduit = produitService.getAllProduit();

		// instancier une catégorie pour éviter l'erreur target unreachable
		this.produit = new Produit();

		this.indice = false;// initialiser l'indice à false pour cacher la table
							// avant la recherche
	}

	// getter et setters
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}

	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
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

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	// le setter pour l'injection de dependance
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	// ==========================================================================
	// Les méthodes métier du ManagedBean

	public String addProduit() {
		// Appel de la méthode add de Service(Etudiant)
		int produitOut = produitService.addProduit(produit);

		if (produitOut != 0) {
			// Mettre à jour la liste des étudiants du prof
			this.listeProduit = produitService.getAllProduit();

			// On est ultra giga méga content et on va dans l'accueil
			return "1_adminConsultation";
		} else {
			return "1_adminAjoutProduit";
		}
	}

	public String updateProduit() {
		// appel de la methode update de service
		int verif = produitService.updateProduit(produit);

		if (verif != 0) {
			// Mettre à jour la liste des étudiants du prof
			this.listeProduit = produitService.getAllProduit();

			// On est on va dans l'accueil
			return "1_adminConsultation";
		} else {
			// ajouter le message d'erreur dans facesContext
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a echouée"));
			return "1_adminModifProduit";
		}
	}

	public String deleteProduit() {
		int verif = produitService.deleteProduit(produit);

		if (verif != 0) {
			// Mettre à jour la liste des étudiants du prof
			this.listeProduit = produitService.getAllProduit();

			// On est on va dans l'accueil
			return "1_adminConsultation";
		} else {
			// ajouter le message d'erreur dans facesContext
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a echouée"));
			return "1_adminSupprProduit";
		}
	}

	public String searchProduitByCategorie() {
		// appel de la methode service
		this.listeProduit = produitService.searchProduitByCategorie(produit, categorie);

		if (this.listeProduit != null) {
			this.indice = true;
		} else {
			this.indice = false;
			// ajouter le message d'erreur dans facesContext
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucun produit pour cette catégorie"));
		}

		return "recherche";
	}
	
	public Produit searchProduitByName() {
		
		// appel de la méthode service
		this.produit = produitService.searchProduitByName(produit);
		
		if (this.produit != null) {
			this.indice = true;
		} else {
			this.indice = false;
			// ajouter le message d'erreur dans facesContext
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le produit n'existe pas"));
		}

		return "recherche";
		
	}

}
