package fr.adaming.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lignescommandes")
public class LigneCommande implements Serializable {
	
	
	//attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_lignecommande")
	private int id;
	private int quantite;
	private double prix;


	//transformation UML en JAVA
	@ManyToOne
	@JoinColumn(name="produit_id",referencedColumnName="id_produit")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name="commande_id", referencedColumnName="id_commande")
	private	Commande commande;
	
	
	//constucteurs 
	public LigneCommande() {
		super();
	}

	public LigneCommande(int quantite, int prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(int id, int quantite, int prix) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.prix = prix;
	}
	
	
	//getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	//tostring
	@Override
	public String toString() {
		return "LigneCommande [id=" + id + ", quantite=" + quantite + ", prix=" + prix + "]";
	}
	
	
	
	
	
	
	
}
