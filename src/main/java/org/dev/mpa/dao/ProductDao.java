package org.dev.mpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.dev.mpa.dto.Merchant;
import org.dev.mpa.dto.Product;

public class ProductDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("development");
	EntityManager manager = factory.createEntityManager();
	
	public Product saveProduct(Product product, int merchant_id) {
		Merchant m = manager.find(Merchant.class, merchant_id);
		EntityTransaction t = manager.getTransaction();
		if(m!=null) {
			product.setMerchant(m);
			m.getProducts().add(product);	
			manager.persist(product);
			t.begin();
			t.commit();		
			return product;
		}
		return null;
		
	}
	
	public Product updateProduct(Product product) {
		EntityTransaction t = manager.getTransaction();
		Product dbProduct = manager.find(Product.class, product.getId());
		if(dbProduct!=null) {
			dbProduct.setName(product.getName());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setBrand(product.getBrand());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setCost(product.getCost());
			dbProduct.setImage_url(product.getImage_url());
			t.begin();
			t.commit();
			return dbProduct;
		}
		return null;
	}
	
	public List<Product> findProductByMerchantId(int id) {
		Query q = manager.createQuery("select m.products from Merchant m where m.id=?1");
		q.setParameter(1, id);
		List<Product> products = q.getResultList();
		return products;
	}
	
	public List<Product> findProductsByBrand(String brand) {
		Query q = manager.createQuery("select p from Product where p.brand=?1");
		q.setParameter(1, brand);
		List<Product> products = q.getResultList();
		return products;
	}
	
	public List<Product> findProductsByCategory(String category) {
		Query q = manager.createQuery("select p from Product where p.category=?1");
		q.setParameter(1, category);
		List<Product> products = q.getResultList();
		return products;
	}
}
