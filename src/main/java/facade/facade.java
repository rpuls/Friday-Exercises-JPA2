/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.Class;

/**
 *
 * @author rasmus
 */
public class facade {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EntityManager em = emf.createEntityManager();
    private static addDelete<Person> adPerson = new addDelete();
    private static addDelete<Student> adStudent = new addDelete();
    private static addDelete<Employee> adEmployee = new addDelete();

    private static class addDelete<Type> {

        public void add(Type obj) {
            try {
                em.getTransaction().begin();
                em.persist(obj);
                em.getTransaction().commit();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }

        public void delete(Type obj) {
            try {
                em.getTransaction().begin();
                em.remove(obj);
                em.getTransaction().commit();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }

    }

    //CREATE
    public void addPerson(Person p) {
        adPerson.add(p);
    }

    public void addStudent(Student p) {
        adStudent.add(p);
    }

    public void addEmployee(Employee p) {
        adEmployee.add(p);
    }

    //READ
    public Person findPerson(int id) {
        return em.find(Person.class, id);
    }

    public Student findStudent(int id) {
        return em.find(Student.class, id);
    }

    public Employee findEmployee(int id) {
        return em.find(Employee.class, id);
    }

    //UPDATE
    
    
    //DELETE
    public void deletePerson(Person p) {
        adPerson.delete(p);
    }

    public void deleteStudent(Student p) {
        adStudent.delete(p);
    }

    public void deleteEmployee(Employee p) {
        adEmployee.delete(p);
    }
}
