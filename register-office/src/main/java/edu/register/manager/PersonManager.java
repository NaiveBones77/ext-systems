package edu.register.manager;

import edu.register.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class PersonManager {
    public static void main(String[] args) {
//        sessionExample();

        jpaExample();

    }

    private static void jpaExample() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person p = new Person();
        p.setLastName("Васильев");
        p.setFirstName("Федор");

        em.persist(p);
        System.out.println(p.getId());

        em.getTransaction().commit();

        List l = em.createQuery("FROM Person ").getResultList();
        l.forEach(person -> System.out.println(person.toString()));
        em.close();
    }

    private static void sessionExample() {
        SessionFactory sf = buildSessionFactory();

        Session session = sf.openSession();

        session.getTransaction().begin();

        Person p = new Person();
        p.setFirstName("Александр");
        p.setLastName("Петров");

        Long id =(Long) session.save(p);

        session.getTransaction().commit();

        Person p2 = session.get(Person.class, new Long(2));
        System.out.println(p2.toString());

        session.close();

        session = sf.openSession();

        List<Person> list = session.createQuery("from Person", Person.class).list();
        list.forEach(person -> System.out.println(person.toString()));

        session.close();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();

            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        }
        catch (Throwable e)
        {
            System.err.println("Failed " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
}
