package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;

@Service("commandeService")
@Transactional
public class CommandeServiceImpl implements ICommandeService {
	
	@Autowired
	private ICommandeDao commandeDao;
	
	//le setter pour l'injection
	public void setCommandeDao(ICommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}
	
	
	@Override
	public List<Commande> getAllCommandes() {
		return commandeDao.getAllCommandes();
	}

	

	@Override
	public Commande addCommande(Commande commandeIn, Client clientIn) {
		commandeIn.setClient(clientIn);
		return commandeDao.addCommande(commandeIn);
	}

	@Override
	public Commande updateCommande(Commande commandeIn) {	
		return commandeDao.updateCommande(commandeIn);
	}

	@Override
	public Commande deleteCommande(Commande commandeIn) {	
		return commandeDao.deleteCommande(commandeIn);
	}

	@Override
	public Commande searchCommandeByID(Commande commandeIn) {
		return commandeDao.searchCommandeByID(commandeIn);
	}

	@Override
	public List<Commande> searchCommandeByDate(Commande commandeIn) {
		return commandeDao.searchCommandeByDate(commandeIn);
	}

}
