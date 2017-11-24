package hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.klasy.Band;
import hibernate.klasy.ObjectsCreator;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class CreateDatabaseTest {



    @Before
    public void setUp(){

    }

    @Test
    public void stworzenieBazy() throws Exception {

        BasicConfigurator.configure();
        ObjectsCreator objectsCreator;
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            objectsCreator = new ObjectsCreator();
            objectsCreator.pobierzZPliku(new ObjectMapper(),"json");
            entityManagerFactory = Persistence.createEntityManagerFactory("BazaMichcio");
            entityManager = entityManagerFactory.createEntityManager();
            assertNotNull(entityManagerFactory);
            assertNotNull(entityManager);

            entityManager.getTransaction().begin();

            List<Band> bands = objectsCreator.getBands();
            assertNotNull(bands);

            for(Band b : bands){
                assertNotNull(b);
                entityManager.persist(b);
            }

            entityManager.close();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            assertNotNull(entityManagerFactory);
            entityManagerFactory.close();
        }
    }

}