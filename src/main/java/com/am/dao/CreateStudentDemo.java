package com.am.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.am.entity.Student;

public class CreateStudentDemo {

    public static void main(String[] args) {

        //SessionFactory Object Created once only for 1DB connection.Use Config object to load confg & properties to load DB.
        SessionFactory sessionFactory = new Configuration()
                .configure()// By default Confg will look for hibernate.cfg.xml(Default Name)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {

            Student newStudent = new Student("Harvey", "Specter", "h.s@psl.com");
            //Start Transaction
            session.beginTransaction();
            //Save it in Db
            session.save(newStudent);
            //Commit it
            session.getTransaction().commit();

            System.out.println("Transaction Saved Successfully " + newStudent.toString());

        } finally {
            sessionFactory.close();
        }
    }

}
