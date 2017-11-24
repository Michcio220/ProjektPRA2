package hibernate.polecenia;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.klasy.Band;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.io.File;

import static org.junit.Assert.*;

public class QueriesTest {

    @Before
    public  void setUp(){

    }

    @Test
    public void getBandsByName() throws  Exception {

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("BazaMichcio");
            entityManager = entityManagerFactory.createEntityManager();
            assertNotNull(entityManager);
            TypedQuery<Band> query = entityManager.createQuery(
                    "SELECT c FROM Band c", Band.class);
            ;

            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

            jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonMapper.registerModule(new JodaModule());
            jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            jsonMapper.writeValue(new File("resultTEST.json."),query.getResultList());

            ObjectMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.registerModule(new JodaModule());
            xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            xmlMapper.writeValue(new File("resultTEST.xml."),query.getResultList());

        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            assertNotNull(entityManagerFactory);
            entityManagerFactory.close();
        }

    }

}