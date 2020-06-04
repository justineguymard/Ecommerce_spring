package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.IAdminDao;
import fr.adaming.entities.Admin;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

	// Transformation de l'association UML en JAVa
	@Autowired
	private IAdminDao adminDao;

	// le setter pour l'injection de dependance avec @Autowired
	public void setProfDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin toLogIn(Admin admin) {
		
		// appel de la méthode dao
		return adminDao.toLogIn(admin);
	}

}
