package com.hibernate.ManyToMany;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.ManyToMany.model.Student;

import com.hibernate.ManyToMany.HibernateUtil;
import com.hibernate.ManyToMany.model.Courses;

public class App 
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            Courses newCourses = new Courses();
            Courses newCourses1 = new Courses();

            Student newStudent = new Student();
            Student newStudent1 = new Student();

            newStudent.setCourses(new ArrayList<>());
            newStudent1.setCourses(new ArrayList<>());

            newCourses.setName("Core_JAVA");
            newCourses1.setName("Advanced_JAVA");

            newStudent.setName("Upendar");
            newStudent.getCourses().add(newCourses);

            newStudent1.setName("Uppi");
            newStudent1.getCourses().add(newCourses1);

            session.save(newCourses);
            session.save(newCourses1);
            session.save(newStudent);
            session.save(newStudent1);

            session.getTransaction().commit();

            session.clear();

            int studentId = newStudent.getStudentId();
            Student retrievedStudent = session.get(Student.class, studentId);

            System.out.println("Retrieved Student: " + retrievedStudent.getName());
            System.out.println("Retrieved Student courses: " + retrievedStudent.getCourses());

            int courseId = newCourses.getCourseId();
            Courses retrievedCourse = session.get(Courses.class, courseId);

            System.out.println("Retrieved Course: " + retrievedCourse.getName());
            System.out.println("Retrieved Course students: " + retrievedCourse.getStudents());

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
