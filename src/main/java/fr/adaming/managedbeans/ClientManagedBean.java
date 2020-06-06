package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.entities.Client;
import fr.adaming.service.IClientService;


@ManagedBean(name="clientMB")
@RequestScoped
public class ClientManagedBean implements Serializable{
	
	//attributs 
	private Client client;
	private List<Client> listeClients;
	private boolean indice;
	
	@ManagedProperty(value="#{clientService}")
	private IClientService clientService;
	
	//Constructeur vide
	public ClientManagedBean() {
		super();
	}
	
	@PostConstruct
	public void init () {
		this.listeClients = clientService.getAllClients();
		this.client = new Client();
		this.indice=false;
		
	}

	//Getters et setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	

	//stter pour l'injection de dépendance
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}
	
	// ============méthode métier 
	
	public String addClient() {
		
		Client clientverif = clientService.addClient(this.client);
		
		if (clientverif != null) {
			this.listeClients=clientService.getAllClients();
			return "3_adminConsultationClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a echoué"));
			return "3_adminAjoutClient";
		}
	}
	
	public String updateClient() {
		
		Client clientverif = clientService.updateClient(this.client);
		
		if (clientverif != null) {
			this.listeClients=clientService.getAllClients();
			return "3_adminConsultationClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a echouée."));
			return "3_adminModifClient";
		}
	}
	
	public String deleteClient() {
		
		Client clientverif = clientService.deleteClient(this.client);
		
		if (clientverif != null) {
			this.listeClients=clientService.getAllClients();
			return "3_adminConsultationClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échouée."));
			return "3_adminSupprClient";
		}
	}
	
	
	public String searchClientByID () {
		List <Client> clienttemp = new ArrayList<Client>();
		Client clientverif = clientService.searchClientByID(this.client);
		
		if (clientverif != null) {
			
			clienttemp.add(clientverif);
			this.listeClients=clienttemp;
			return "3_adminConsultationClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de client avec cet ID."));
			return "3_adminConsultationClient";
		}
	}
	
	public String searchClientByNom () {
		
		List <Client> clientverif = clientService.searchClientByNom(this.client);
		
		if (clientverif.size() != 0) {
			this.listeClients=clientverif;
			return "3_adminConsultationClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de client(s) portant ce nom."));
			return "3_adminConsultationClient";
		}
	}
	
	

}
