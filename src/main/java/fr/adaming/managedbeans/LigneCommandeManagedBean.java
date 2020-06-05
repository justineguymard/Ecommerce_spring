package fr.adaming.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.entities.Admin;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "ligneCommandeMB")
@RequestScoped
public class LigneCommandeManagedBean {

	// DEclaration des attributs du managedBeans qui vont ecapsuler le modele
	// MVC2
	private LigneCommande ligneCommande;
	private Produit produit;
	private List<Produit> listeProduit;
	private Commande commande;
	private List<Commande> listeCommande;
	private boolean indice;

	// Transformation de l'association UML en JAVA
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
		this.ligneCommande = (LigneCommande) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("lgSession");

		// recuperer la liste lors de l'instiation du ManagedBean
		this.listeProduit = produitService.getAllProduit();

		// instancier une catégorie pour éviter l'erreur target unreachable
		this.produit = new Produit();

		// recuperer la liste lors de l'instiation du ManagedBean
		this.listeCommande = commandeService.getAllCommande();

		// instancier une catégorie pour éviter l'erreur target unreachable
		this.commande = new Commande();

		this.indice = false;// initialiser l'indice à false pour cacher la table
		// avant la recherche

	}

}
