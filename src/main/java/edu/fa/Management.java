package edu.fa;

import org.hibernate.Session;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.xml.XMLStreamConstantsUtils;
import org.hibernate.service.ServiceRegistry;

import edu.fa.model.Course;

public class Management {
	public void createCourse(Course course) {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();

		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// add
		session.save(course);

		session.getTransaction().commit();
		sessionFactory.close();
	}

	public static void main(String[] args) {

		Course course1 = new Course("java");
		Course course2 = new Course("python");
		Course course3 = new Course("javascript");

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();

		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// add
		session.save(course1);
		session.save(course2);
		session.save(course3);

		session.getTransaction().commit();
		session.close();

		// delete
		Session session1 = sessionFactory.openSession();
		session1.beginTransaction();
		session1.delete(course1);
		session1.getTransaction().commit();
		session1.close();

		// read
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		Course coursePrint = (Course) session2.get(Course.class, 2);
		System.out.println("in ra thong tin " + coursePrint.getName());
		session2.close();

		// update
		Session session3 = sessionFactory.openSession();
		session3.beginTransaction();
		Course courseUpdate = (Course) session3.get(Course.class, 2);
		courseUpdate.setName("html");
		session3.update(courseUpdate);

		session3.close();

		// close factory
		sessionFactory.close();

	}
}
