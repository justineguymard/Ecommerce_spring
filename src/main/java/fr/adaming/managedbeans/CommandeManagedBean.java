package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.service.ICommandeService;


@ManagedBean(name="commandeMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {
		
		//attributs 
		private Commande commande;
		private List<Commande> listeCommandes;
		private boolean indice;
		private Client client;
		
		@ManagedProperty(value="#{commandeService}")
		private ICommandeService commandeService;
		
		//Constructeur vide
		public CommandeManagedBean() {
			super();
		}
		
		@PostConstruct
		public void init () {
			
			this.listeCommandes =commandeService.getAllCommandes();
			this.commande = new Commande();
			this.indice=false;
			this.client = new Client();
		}

		//Getters et setters
		public Commande getCommande() {
			return commande;
		}

		public void setCommande(Commande commande) {
			this.commande = commande;
		}

		public List<Commande> getListeCommandes() {
			return listeCommandes;
		}

		public void setListeCommandes(List<Commande> listeCommandes) {
			this.listeCommandes = listeCommandes;
		}

		public boolean isIndice() {
			return indice;
		}

		public void setIndice(boolean indice) {
			this.indice = indice;
		}

		

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		//stter pour l'injection de dépendance
		public void setCommandeService(ICommandeService commandeService) {
			this.commandeService = commandeService;
		}
		
		// ============méthode métier 
		
		public String addCommande() {
			
			Commande commandeverif = commandeService.addCommande(this.commande, this.client);
			
			if (commandeverif != null) {
				this.listeCommandes=commandeService.getAllCommandes();
				return "3_adminConsultationCommande";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a echoué"));
				return "3_adminAjoutCommande";
			}
		}
		
		public String updateCommande() {
			
			Commande commandeverif = commandeService.updateCommande(this.commande);
			
			if (commandeverif != null) {
				this.listeCommandes=commandeService.getAllCommandes();
				return "3_adminConsultationCommande";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a echouée."));
				return "3_adminModifCommande";
			}
		}
		
		public String deleteCommande() {
			
			Commande commandeverif = commandeService.deleteCommande(this.commande);
			
			if (commandeverif != null) {
				this.listeCommandes=commandeService.getAllCommandes();
				return "3_adminConsultationCommande";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échouée."));
				return "3_adminSupprCommande";
			}
		}
		
		
		public String searchCommandeByID () {
			
			Commande commandeverif = commandeService.searchCommandeByID(this.commande);
			
			if (commandeverif != null) {
				this.listeCommandes=commandeService.getAllCommandes();
				return "3_adminConsultationCommande";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de commande avec cet ID."));
				return "3_adminConsultationCommande";
			}
		}
		
		public String searchCommandeByDate() {
			
			List <Commande> commandeverif = commandeService.searchCommandeByDate(this.commande);
			
			if (commandeverif.size() != 0) {
				this.listeCommandes=commandeService.getAllCommandes();
				return "3_adminConsultationCommande";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de commande(s) portant ce nom."));
				return "3_adminConsultationCommande";
			}
		}
		
		

	
}
