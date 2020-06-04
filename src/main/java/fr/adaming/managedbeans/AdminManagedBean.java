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
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@ManagedBean(name = "adminMB")
@RequestScoped
public class AdminManagedBean implements Serializable {

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

	public AdminManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {

		// recuperer le prof de la session
		this.prof = (Formateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fSession");

		// recuperer la liste lors de l'instiation du ManagedBean
		this.etudiants = etudiantService.getAllEtudiants(prof);

		// instancier un etudiant pour éviter l'erreur target unreachable
		this.etudiant = new Etudiant();

		this.indice = false;// initialiser l'indice à false pour cacher la table
							// avant la recherche

	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	// le setter pour l'injection de dependance
	public void setEtudiantService(IEtudiantService etudiantService) {
		this.etudiantService = etudiantService;
	}

	// Les méthodes métier du ManagedBean
	public String ajouter() {
		// Appel de la méthode add de Service(Etudiant)
		Etudiant eOut = etudiantService.addEtudiant(etudiant, prof);

		if (eOut.getId() != 0) {
			// Mettre à jour la liste des étudiants du prof
			this.etudiants = etudiantService.getAllEtudiants(prof);

			// On est ultra giga méga content et on va dans l'accueil
			return "accueil";
		} else {
			return "ajout";
		}
	}

	public String modifier() {
		// appel de la methode update de service
		int verif = etudiantService.updateEtudiant(etudiant, prof);

		if (verif != 0) {
			// Mettre à jour la liste des étudiants du prof
			this.etudiants = etudiantService.getAllEtudiants(prof);

			// On est on va dans l'accueil
			return "accueil";
		} else {
			// ajouter le message d'erreur dans facesContext
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modif a echoué"));
			return "modif";
		}
	}

	public String rechercher() {
		// appel de la methode service
		this.etudiant = etudiantService.getEtudiantById(etudiant, prof);

		if (this.etudiant != null) {
			this.indice = true;
		} else {
			this.indice = false;
			// ajouter le message d'erreur dans facesContext
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'etudiant n'existe pas"));
		}

		return "recherche";
	}

	public String supprimer() {
		boolean verif = etudiantService.deleteEtudiant(etudiant, prof);

		if (verif) {
			// Mettre à jour la liste des étudiants du prof
			this.etudiants = etudiantService.getAllEtudiants(prof);

			// On est on va dans l'accueil
			return "accueil";
		} else {
			// ajouter le message d'erreur dans facesContext
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a echoué"));
			return "suppression";
		}
	}

}
