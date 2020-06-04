package fr.adaming.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

@Repository
public class EtudiantDaoImpl implements IEtudiantDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public List<Etudiant> getAllEtudiant(Formateur prof) {

		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		// construire la requete HQL
		String req = "FROM Etudiant e WHERE e.professeur.id=:pIdProf";

		// creation d'un objet de type Query
		Query query = s.createQuery(req);

		// passage des params
		query.setParameter("pIdProf", prof.getId());

		// envoyer la requete et recuperer la liste
		return query.list();
	}

	@Override
	public Etudiant addEtudiant(Etudiant e) {

		// Récup la session
		Session s = sf.getCurrentSession();

		// Appel de la méthode pour sauvegarder etudiant dans la base de données
		s.save(e);

		return e;
	}

	@Override
	public int updateEtudiant(Etudiant e) {
		// recuperer la session d'hibernate
		Session s = sf.getCurrentSession();

		// ecrire la requete HQL
		String req = "UPDATE Etudiant e SET e.nom=:pNom, e.prenom=:pPrenom, e.dn=:pDn WHERE e.id=:pId AND e.professeur.id=:pIdProf";
		
		// recuperer le query pour envoyer la requete HQL
		Query query=s.createQuery(req);
		
		// passage des params
		query.setParameter("pNom", e.getNom());
		query.setParameter("pPrenom", e.getPrenom());
		query.setParameter("pDn", e.getDn());
		query.setParameter("pId", e.getId());
		query.setParameter("pIdProf", e.getProfesseur().getId());
		
		
		// envoyer la requete HQL et recup du resultat
		return query.executeUpdate();
	}

	@Override
	public Etudiant getEtudiantById(Etudiant e) {
		
		// recuperer la session d'hibernate 
		Session s=sf.getCurrentSession();
		
		// ecrire la requete HQL
		String req="FROM Etudiant e WHERE e.id=:pId AND e.professeur.id=:pIdProf";

		// recup due l'objet Query
		Query query=s.createQuery(req);
		
		// passage des params
		query.setParameter("pId", e.getId());
		query.setParameter("pIdProf", e.getProfesseur().getId());
		
		return (Etudiant) query.uniqueResult();
	}

	@Override
	public boolean deleteEtudiant(Etudiant e) {
		// recuperer la session
		Session s=sf.getCurrentSession();
		
		try{
			s.delete(e);
			return true;
		}catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
