package org.dev.mpa.controller;

import java.util.List;
import java.util.Scanner;

import org.dev.mpa.dao.ProductDao;
import org.dev.mpa.dto.Merchant;
import org.dev.mpa.dto.Product;

public class ProductController {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Save Product");
		System.out.println("2.Update Product");
		System.out.println("3.Find Products By Merchant Id");
		System.out.println("4.Find Products by brand");
		System.out.println("5.Find Products by category");

		switch (sc.nextInt()) {
		case 1:{
			Product p = new Product();
			System.out.println("Enter merchant id to add product");
			int id = sc.nextInt();
			System.out.println("Enter name, brand, category, description, image_url, cost");
			p.setName(sc.next());
			p.setBrand(sc.next());
			p.setCategory(sc.next());
			p.setDescription(sc.next());
			p.setImage_url(sc.next());
			p.setCost(sc.nextDouble());

			p = productDao.saveProduct(p, id);
			if(p!=null) {
				System.out.println("Product saved with id " + p.getId());
			}else {
				System.out.println("Invalid merchant id!");
			}
			break;
		}
		case 2: {
			Product p = new Product();
			System.out.println("Enter id, name, brand, category, description, image_url, cost");
			p.setId(sc.nextInt());
			p.setName(sc.next());
			p.setBrand(sc.next());
			p.setCategory(sc.next());
			p.setDescription(sc.next());
			p.setImage_url(sc.next());
			p.setCost(sc.nextDouble());
			productDao.updateProduct(p);
			System.out.println("Product updated with id " + p.getId());
			break;
		}
		case 3: {
			System.out.println("Enter merchant id:");
			List<Product> products = productDao.findProductByMerchantId(sc.nextInt());
			if(products.size()>0) {
				for(Product p: products) {
					System.out.println(p);
				}
			}else {
				System.out.println("No product registered with this merchant id");
			}
			break;
		}
		case 4: {
			System.out.println("Enter brand");
			List<Product> products = productDao.findProductsByBrand(sc.next());
			if(products.size()>0) {
				for(Product p: products) {
					System.out.println(p);
				}
			}else {
				System.out.println("No product present with this brand");
			}
			break;
		}
		case 5: {
			System.out.println("Enter category");
			List<Product> products = productDao.findProductsByCategory(sc.next());
			if(products.size()>0) {
				for(Product p: products) {
					System.out.println(p);
				}
			}else {
				System.out.println("No product present with this category");
			}
			break;
		}
		default:
			break;
		}
	}
}	
