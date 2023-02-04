package com.mypack.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypack.hibernate.entity.Employee;

public class HibernateConfig {

	private static SessionFactory sessionFactory = null;

	public static SessionFactory createSessionFactory() {
		try {

			Configuration configuration = new Configuration();
			sessionFactory = configuration.addAnnotatedClass(Employee.class).configure().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

}
