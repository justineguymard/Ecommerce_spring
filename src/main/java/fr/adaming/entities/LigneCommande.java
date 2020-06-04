package fr.adaming.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	private int prix;


	//transformation UML en JAVA
	@Many
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
	
	
	
	
	
}
