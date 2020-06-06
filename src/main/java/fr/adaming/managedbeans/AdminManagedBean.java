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
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "adminMB")
@RequestScoped
public class AdminManagedBean implements Serializable {

	// DEclaration des attributs du managedBeans qui vont ecapsuler le modele
	// MVC2
	private Admin admin;

	// Transformation de l'association UML en JAVA
	@ManagedProperty(value = "#{adminService}")
	private IAdminService adminService;

	// constructeur
	public AdminManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {

		// recuperer le prof de la session
		this.admin = new Admin();

	}

	// getters et setters
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	// le setter pour l'injection de dependance
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	// ==========================================================================
	// Les méthodes métier du ManagedBean

	public String seConnecter() {
		// appel de la methode service pour chercher le formateur
		Admin adminOut = adminService.toLogIn(admin);

		if (adminOut != null) {

			// ajouter le formateur cennecté dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adminOut);

			return "1_1_adminConsultation";
		} else {
			return "1_0_adminLogin";
		}
	}

	public String seDeconnecter() {
		// fermer la session actuelle du conseiller
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		// ajouter le message utilisateur pour l'echec de connexion
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vous êtes déconnectés !"));

		return "1_0_adminLogin";
	}

	public String closeAppli() {
		// fermer la session actuelle du conseiller
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "0_accueilSite";
	}

}
