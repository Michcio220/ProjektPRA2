package hibernate.zplikudobazy;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hibernate.klasy.ObjectsCreator;
import hibernate.klasy.Band;
import org.apache.log4j.BasicConfigurator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CreateDatabase{

    public CreateDatabase() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        StworzenieBazy(jsonMapper,"json");
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        //StworzenieBazy(xmlMapper,"xml");
    }


    //funkcja wprowadzajaca dane z pliku do bazy
    public static void StworzenieBazy(ObjectMapper mapper, String fileSuffix) throws Exception{

        BasicConfigurator.configure();

        EntityManager entityManager = null;
        EntityManagerFactory entityManagerFactory = null;

        ObjectsCreator objectsCreator = new ObjectsCreator();
        objectsCreator.pobierzZPliku(mapper,fileSuffix);

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("BazaMichcio");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Band> bands = objectsCreator.getBands();
            for(Band b : bands){
                entityManager.persist(b);
            }
            entityManager.getTransaction().commit();

            entityManager.close();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
        } finally{
            assert null != entityManagerFactory;
            entityManagerFactory.close();
        }
    }

    public static void main(String[] args) throws Exception{

        CreateDatabase createDatabase = new CreateDatabase();

    }
}
