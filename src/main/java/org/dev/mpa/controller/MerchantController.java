package org.dev.mpa.controller;

import java.util.Scanner;

import org.dev.mpa.dao.MerchantDao;
import org.dev.mpa.dto.Merchant;

public class MerchantController {
	public static void main(String[] args) {
		MerchantDao merchantDao = new MerchantDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Save Merchant");
		System.out.println("2.Update Merchant");
		System.out.println("3.Find Merchant By Id");
		System.out.println("4.Verify Merchant by email and password");
		System.out.println("5.Verify Merchant by phone and password");
		while(true) {
			switch(sc.nextInt()) {
			case 1: {
				Merchant m = new Merchant();
				System.out.println("Enter name, phone, email, gst_number, password");
				m.setName(sc.next());
				m.setPhone(sc.nextLong());
				m.setEmail(sc.next());
				m.setGst_number(sc.next());
				m.setPassword(sc.next());
				m = merchantDao.saveMerchant(m);
				System.out.println("Merchant saved with id " + m.getId());
				break;		
			}
			case 2: {
				Merchant m = new Merchant();
				System.out.println("Enter id, name, phone, email, gst_number, password");
				m.setId(sc.nextInt());
				m.setName(sc.next());
				m.setPhone(sc.nextLong());
				m.setEmail(sc.next());
				m.setGst_number(sc.next());
				m.setPassword(sc.next());
				m = merchantDao.updateMerchant(m);
				if(m!=null) {
					System.out.println("Merchant updated with id " + m.getId());
				}else {
					System.out.println("Invalid merchant id");
				}
				break;		
			}
			case 3: {
				System.out.println("Enter merchant id:");
				Merchant m = merchantDao.findMerchantById(sc.nextInt());
				System.out.println("Merchant found with id" + m.getId());
				break;
			}
			case 4:{
				System.out.println("Enter merchant email and password");
				Merchant m = merchantDao.verifyMerchant(sc.next(), sc.next());
				System.out.println("Merchant found with id: " + m.getId());
				break;
			}
			case 5:{
				System.out.println("Enter merchant phone and password");
				Merchant m = merchantDao.verifyMerchant(sc.nextLong(), sc.next());
				System.out.println("Merchant found with id: " + m.getId());
				break;
			}
			default: {
				System.out.println("Wrong input");
			}
			}
		}
	}
}
