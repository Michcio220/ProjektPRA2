package hibernate.polecenia;

import hibernate.klasy.Album;
import hibernate.klasy.Band;
import hibernate.klasy.Song;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class Queries {

    EntityManager entityManager;

    public Queries(EntityManager entityManager){this.entityManager = entityManager;}

    //Poleceni nr.1
    public List<Band> getBandsByName(){
        TypedQuery<Band> query = entityManager.createQuery(
                "SELECT c FROM Band c", Band.class);
        return query.getResultList();
    }

    //Polecenie nr.2
    public List<Band> getBandssByType(String type){
        TypedQuery<Band> query = entityManager.createQuery(
                "SELECT c FROM Band c WHERE c.typ LIKE :type ", Band.class);
        return query.setParameter("type",type).getResultList();
    }

    //Polecenie nr.3
    public List<Band> getBandBySongs(String nazwa){
       Query query = entityManager.createQuery(
                "SELECT c.band FROM Song c WHERE c.band.nazwa LIKE :nazwa",Song.class);
       return query.setParameter("nazwa",nazwa).getResultList();
    }

    //Polecenie nr.4
    public List<Band> getBandBySongName(String album){
        Query query = entityManager.createQuery(
                "SELECT a FROM Album a WHERE a.nazwaAlbumu LIKE :album ", Album.class
        );
        return query.setParameter("album",album).getResultList();
    }

    //Polecenie nr.5
    public List<Band> getBandByPage(int number){
        //first query
        Query query = entityManager.createQuery(
                "SELECT count(b) FROM Band b" , Band.class);
        long countResult = (long)query.getSingleResult();

        //second query
        Query query1 = entityManager.createQuery(
                "SELECT b FROM Band b"
        );

        int pageSize = 10;
        int pageNumber = (int)((countResult/pageSize) +1);

        if(number > pageNumber){ number = pageNumber;}
        query.setFirstResult((number - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query1.getResultList();
    }

}