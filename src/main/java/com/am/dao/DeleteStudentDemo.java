package com.am.dao;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.am.entity.Student;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        //SessionFactory Object Created once only for 1DB connection.Use Config object to load confg & properties to load DB.
        SessionFactory sessionFactory = new Configuration().configure()// By default Confg will look for hibernate.cfg.xml(Default Name)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter Id for which you want to delete data");
            int id = sc.nextInt();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting Data Read from DB " + id);

            Student myStudent = session.get(Student.class, id);
            System.out.println("D Read..." + myStudent);
            session.delete(myStudent);
            session.getTransaction().commit();
            System.out.println("Deleted data..." + myStudent);

        } finally {
            sessionFactory.close();
        }
    }

}
