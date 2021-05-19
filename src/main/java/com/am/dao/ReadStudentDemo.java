package com.am.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.am.entity.Student;

public class ReadStudentDemo {

    public static void main(String[] args) {

        //SessionFactory Object Created once only for 1DB connection.Use Config object to load confg & properties to load DB.
        SessionFactory sessionFactory = new Configuration().configure()// By default Confg will look for hibernate.cfg.xml(Default Name)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating Student Object");
            Student newStudent = new Student("Harvey", "Specter", "h.s@psl.com");
            //Start Transaction
            session.beginTransaction();
            //Save it in Db
            session.save(newStudent);
            //Commit it
            session.getTransaction().commit();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting Data Read from DB " + newStudent.getId());

            Student myStudent = session.get(Student.class, newStudent.getId());
            System.out.println("Data Read..." + myStudent);

            //Query All records

            List<Student> myList = session.createQuery("from Student where firstName='Mike'").getResultList();
            System.out.println("List of Students :: " + myList);
            List<Student> myList2 = session.createQuery(
                    "from Student where firstName='Harvey' OR lastName='Ross'")
                    .getResultList();
            System.out.println("List of New Students :: " + myList2);

        } finally {
            sessionFactory.close();
        }
    }

}
