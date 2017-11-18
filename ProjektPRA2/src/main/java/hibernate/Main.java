package hibernate;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.klasy.Album;
import hibernate.klasy.Band;
import hibernate.klasy.Song;
import org.apache.log4j.BasicConfigurator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public void StworzenieBazy(ObjectMapper mapper, String fileSuffix) throws Exception{


        InputStream bandIs2 = Jserialization.class.getClassLoader().
                getResourceAsStream("musician." + fileSuffix);
        List<Band> bands = mapper.readValue(bandIs2,new TypeReference<List<Band>>(){});
        String json = mapper.writeValueAsString(bands);
        System.out.println(json);



    }

    public static void main(String[] args){

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
            bad.setTyp("Rock");
            bad.setRok(1997);
            bad.setLiczba(4);

            Album alb = new Album();
            alb.setNazwaAlbumu("Pink Memories");
            alb.setRokwydania(1982);

            Song song = new Song();
            song.setNazwa("Another Brick in the wall");
            song.setRokWydania(1982);
            song.setBand(bad);
            song.setWykonawca();

            alb.getSongs().add(song);
            bad.getSongs().add(song);
            bad.getAlbums().add(alb);

            List<Band> bands = new ArrayList<Band>();
            bands.add(bad);
            for(Band f : bands){
                entityManager.persist(f);
            }

          //  entityManager.persist(bad);
          //  entityManager.persist(song);
          //  entityManager.persist(alb);

            entityManager.getTransaction().commit();

            System.out.println("Done");

            entityManager.close();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
        } finally{
            assert null != entityManagerFactory;
            entityManagerFactory.close();
        }
    }
}
