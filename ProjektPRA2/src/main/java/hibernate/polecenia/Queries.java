package hibernate.polecenia;

import hibernate.klasy.Band;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class Queries {

    EntityManager entityManager;

    public Queries(EntityManager entityManager){this.entityManager = entityManager;}

    public List<Band> getBandsByName(){

        TypedQuery<Band> query = entityManager.createQuery(
                "SELECT c FROM Band c", Band.class);
        return query.getResultList();

    }

    public List<Band> getBandssByType(String type){
        TypedQuery<Band> query = entityManager.createQuery(
                "SELECT c FROM Band c WHERE c.typ LIKE :type ", Band.class);
        return query.setParameter("type",type).getResultList();
    }

//    public List<Band> getBandBySongs{
//          TypedQuery<Band> query = entityManager.createQuery()
//    }

}