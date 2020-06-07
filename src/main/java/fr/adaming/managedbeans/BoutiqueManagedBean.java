package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;

@ManagedBean(name="pannierMB")
@SessionScoped
public class BoutiqueManagedBean implements Serializable{
	
	private List <LigneCommande> listeLignesCommande; 
	private Client client;
	private Commande commande;
	private Produit produit;
	
	
	public BoutiqueManagedBean() {
		super();
	}
	
	@PostConstruct
	public void init () {
		
		this.listeLignesCommande = new ArrayList<>();
		this.client = new Client ();
		this.commande = new Commande();
		this.produit = new Produit();	
		
	}
	

	
	

}
