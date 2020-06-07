package fr.adaming.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;

@Service("panierService")
@Transactional
public class PanierServiceImpl implements IPanierService {

	@Override
	public LigneCommande addProduitPanier(int quantite) {
		
		
		return null;
	}

	
	

}
