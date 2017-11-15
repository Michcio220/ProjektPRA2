package hibernate;


import hibernate.klasy.Band;
import hibernate.klasy.Utwory;
import org.apache.log4j.BasicConfigurator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        System.out.println("Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("BazaMichcio");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            Band bad = new Band();
            bad.setNazwa("Pink Floyd");
            bad.setBandId(new Random().nextInt(100));
            bad.setLiczba(4);
            bad.setRok(1977);
            bad.setTyp("Rock");

            Utwory utwor = new Utwory();
            utwor.setNazwa("Another Brick In The Wall");
            utwor.setRokWydania(1982);
            utwor.setIdUtwory(new Random().nextInt(100));

            bad.addUtwor(utwor);

            entityManager.persist(utwor);
            entityManager.persist(bad);

            System.out.println("Done");

            entityManager.close();

        }catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
        }finally{
            assert null != entityManagerFactory;
            entityManagerFactory.close();
        }

    }
}
