package com.am.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.am.entity.Instructor;
import com.am.entity.InstructorDetail;

public class MappingDemoOneToOne {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernateMapping.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            InstructorDetail instructorDetail = new InstructorDetail("Suits", "Prodigal Son");
            Instructor instructorObject = new Instructor("Mike", "Ross", "m.R@psl.com");
            //Uni Directional One to One
            //instructorObject.setInstructorDetail(instructorDetail);

            //Bi Directional One to One
            instructorDetail.setInstructor(instructorObject);

            session.beginTransaction();
            session.save(instructorDetail);
            System.out.println("Saving Instructor Object ...........");
            session.getTransaction().commit();
            System.out.println("Object Saved to DB :: " + instructorDetail.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
            session.close();
        }
    }

}
