package hibernate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.klasy.Album;
import hibernate.klasy.Band;
import hibernate.klasy.Song;
import hibernate.polecenia.Queries;
import org.apache.log4j.BasicConfigurator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String [] args){

        BasicConfigurator.configure();
        System.out.println("Start");

        EntityManager entityManager = null;
        EntityManagerFactory entityManagerFactory = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("BazaMichcio");
            entityManager = entityManagerFactory.createEntityManager();

            Queries query = new Queries(entityManager);
            List<Band> bands = query.getBandsByName();
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

            jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonMapper.registerModule(new JodaModule());
            jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            jsonMapper.writeValue(new File("result1.json"),bands);

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
        }finally{
            assert null != entityManagerFactory;
            entityManagerFactory.close();
        }
    }


}
