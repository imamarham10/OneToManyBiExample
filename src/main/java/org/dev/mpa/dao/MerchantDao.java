package org.dev.mpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.dev.mpa.dto.Merchant;

public class MerchantDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("development");
	EntityManager manager = factory.createEntityManager();
	
	public Merchant saveMerchant(Merchant merchant) {
		EntityTransaction t = manager.getTransaction();
		manager.persist(merchant);
		t.begin();
		t.commit();		
		return merchant;
	}
	
	public Merchant updateMerchant(Merchant merchant) {
		EntityTransaction t = manager.getTransaction();
		Merchant dbMerchant = manager.find(Merchant.class, merchant.getId());
		if(dbMerchant!=null) {
			dbMerchant.setName(merchant.getName());
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPassword(merchant.getPassword());
			t.begin();
			t.commit();
			return dbMerchant;
		}
		return null;
	}
	
	public Merchant findMerchantById(int id) {
		return manager.find(Merchant.class, id);
	}
	
	public Merchant verifyMerchant(long phone, String password) {
		Query q = manager.createQuery("select m from Merchant m where phone=?1 and password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		
		try {
			return (Merchant)q.getSingleResult();
			
		}catch(NoResultException exp) {
			return null;
		}
	}
	
	public Merchant verifyMerchant(String email, String password) {
		Query q = manager.createQuery("select m from Merchant m where email=?1 and password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		
		try {
			return (Merchant)q.getSingleResult();
			
		}catch(NoResultException exp) {
			return null;
		}
	}

}
