package it.unical.uniexam.hibernate.util;


import it.unical.uniexam.hibernate.domain.MatrDetails;
import it.unical.uniexam.hibernate.domain.PhoneNumber;
import it.unical.uniexam.hibernate.domain.Student;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static{
		try{
			//ClassLoader.getSystemResource("resources/hibernate.cfg.xml")
			sessionFactory=new Configuration()
			.configure()
			.addPackage("it.unical.uniexam.hibernate.domain")
			.addAnnotatedClass(Student.class)
			.addAnnotatedClass(PhoneNumber.class)
			.addAnnotatedClass(MatrDetails.class)
			.buildSessionFactory();
		}catch(Exception e){

		}
	}
	//si devono aggiungere tutte le classi che vogliamo siano utilizzate nel db

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}


}