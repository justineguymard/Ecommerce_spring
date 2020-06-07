package fr.adaming.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IPanierDao;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Panier;
import fr.adaming.entities.Produit;

@Service("panierService")
@Transactional
public class PanierServiceImpl implements IPanierService {

	private IPanierDao panierDao;
	
	
	
	
	public void setPanierDao(IPanierDao panierDao) {
		this.panierDao = panierDao;
	}

	@Override
	public List<Produit> produitsSelectionnes() {
		// TODO Auto-generated method stub
		return panierDao.produitsSelectionnes();
	}

	@Override
	public Produit GetProduit(Long idProduit) {
		// TODO Auto-generated method stub
		return panierDao.GetProduit(idProduit);
	}

	@Override
	public void supprimerProduit(Long idProduit) {
		panierDao.supprimerProduit(idProduit);
		
	}

	@Override
	public Commande enrCommande(Panier panierIn, Client clientIn) {	
		return panierDao.enrCommande(panierIn, clientIn);
	}



	
	

}
