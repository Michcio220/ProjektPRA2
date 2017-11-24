package hibernate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hibernate.klasy.Album;
import hibernate.klasy.Band;
import org.junit.Before;
import org.junit.Test;


import java.io.InputStream;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;

public class ObjectsCreatorTest {



    @Before
    public void setUp(){ }

    @Test
    public void pobierzZPliku() throws Exception {

        List<Band> bandsJson;
        List<Band> bandsXml;

        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectMapper xmlMapper = new XmlMapper();

        InputStream jsonBands = ObjectsCreator.class.getClassLoader().
                getResourceAsStream("musician.json");
        bandsJson = jsonMapper.readValue(jsonBands,new TypeReference<List<Band>>(){});

        InputStream xmlBands = ObjectsCreator.class.getClassLoader().
                getResourceAsStream("musician.xml");
        bandsXml = xmlMapper.readValue(xmlBands,new TypeReference<List<Band>>(){});

        //Sprawdzanie czy tablica nie jest pusta
        assertNotNull(bandsJson);
        assertNotNull(bandsXml);
        //Sprawdzanie czy tablica nie posiada pustego pola
        for(Band b : bandsJson){
            assertNotNull(b.getNazwa());
            assertNotNull(b.getTyp());
            for(Album a : b.getAlbums()){
                assertNotNull(a.getNazwaAlbumu());
            }
        }
    }


}