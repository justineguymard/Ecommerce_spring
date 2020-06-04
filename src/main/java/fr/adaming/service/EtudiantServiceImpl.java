package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.EtudiantDaoImpl;
import fr.adaming.dao.IEtudiantDao;
import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

@Service("eService")
@Transactional
public class EtudiantServiceImpl implements IEtudiantService {

	// TRansformation de l'association UML en JAVA
	@Autowired // injectiojn de dependance
	private IEtudiantDao etudiantDao;

	// Le setter pour l'injection
	public void setEtudiantDao(IEtudiantDao etudiantDao) {
		this.etudiantDao = etudiantDao;
	}

	@Override
	public List<Etudiant> getAllEtudiants(Formateur prof) {
		// appel de la methode Dao
		return etudiantDao.getAllEtudiant(prof);
	}

	@Override
	public Etudiant addEtudiant(Etudiant e, Formateur prof) {
		// Lier le prof à l'étudiant
		e.setProfesseur(prof);

		// Appel de la méthode dao add
		return etudiantDao.addEtudiant(e);
	}

	@Override
	public int updateEtudiant(Etudiant e, Formateur prof) {
		// Lier le prof à l'étudiant
		e.setProfesseur(prof);

		// Appel de la méthode dao
		return etudiantDao.updateEtudiant(e);
	}

	@Override
	public Etudiant getEtudiantById(Etudiant e, Formateur prof) {
		/// Lier le prof à l'étudiant
		e.setProfesseur(prof);

		// Appel de la méthode daos
		return etudiantDao.getEtudiantById(e);
	}

	@Override
	public boolean deleteEtudiant(Etudiant e, Formateur prof) {
		// Lier le prof à l'étudiant
		e.setProfesseur(prof);

		// recuperer l'etudiant de la bd
		Etudiant eOut = etudiantDao.getEtudiantById(e);

		if (eOut != null) {
			// appel de la methode dao
			return etudiantDao.deleteEtudiant(eOut);
		}

		return false;
	}

}
