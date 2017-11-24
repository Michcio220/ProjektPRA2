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
import org.hibernate.query.internal.QueryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void wykonajQuery(EntityManager entityManager) throws Exception{

        Queries query = new Queries(entityManager);
        List<Band> bands = query.getBandBySongName("Let It Be");
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonMapper.registerModule(new JodaModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.writeValue(new File("result.json"),bands);

        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.registerModule(new JodaModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.writeValue(new File("result.xml"),bands);

    }

    public static void main(String [] args){

        BasicConfigurator.configure();
        System.out.println("Start");

        EntityManager entityManager = null;
        EntityManagerFactory entityManagerFactory = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("BazaMichcio");
            entityManager = entityManagerFactory.createEntityManager();

            wykonajQuery(entityManager);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm, dd-MM-yy");

            LocalDateTime ldt = LocalDateTime.now();

            ZonedDateTime zonedDateTime =  ldt.atZone(ZoneId.of("Europe/Paris"));
            System.err.println(format.format(zonedDateTime));

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
        }finally{
            assert null != entityManagerFactory;
            entityManagerFactory.close();
        }
    }

}
