package hibernate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import hibernate.klasy.Album;
import hibernate.klasy.Band;
import hibernate.klasy.Song;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Klasa ktora pomaga tworzyc pliki danych json/xml
public class MyMObjectsCreator {

    List<Band> bands;
    List<Song> songs;
    List<Album> albums;

    public List<Band> getBands() { return bands; }

    public List<Song> getSongs() { return songs; }

    public List<Album> getAlbums() { return albums; }

    public void zapiszDoPliku(ObjectMapper mapper , String filesuffix) throws IOException {

        //Pierwszy zespol
        Band bad = new Band("FAKE TYPE",2013,2,"HipHop");
        Song song = new Song("Devil's Wedding",2013,bad);
        Song song1 = new Song("Bacchus",2014,bad);
        Song song2 =  new Song("MONSTER'S ZOO",2014,bad);
        bad.getSongs().add(song); bad.getSongs().add(song1); bad.getSongs().add(song2);
        Album album = new Album("FAKE BOOK",2013);
        album.getSongs().add(song);album.getSongs().add(song1);album.getSongs().add(song2);
        bad.getAlbums().add(album);
        bands.add(bad);

        //Drugi zespol
        Band bad1 = new Band("The Beatles",1960,4,"Rock");
        Song song11 = new Song("Two Of Us",1970,bad1);
        Song song12 = new Song("Dig A Pony",1970,bad1);
        Song song13 = new Song("Across The Universe",1970,bad1);
        bad1.getSongs().add(song11); bad1.getSongs().add(song12); bad1.getSongs().add(song13);
        Album album1 = new Album("Let It Be",1970);
        album1.getSongs().add(song11); album1.getSongs().add(song12); album1.getSongs().add(song13);
        bad1.getAlbums().add(album1);
        bands.add(bad1);

        //Trzeci zespol
        Band bad2 = new Band("Pink Floyd",1965,5," ProgressiveRock");
        Song song21 = new Song("The Thin Ice",1979,bad2);
        Song song22 = new Song("Another Brick In The Wall,Part 1",1979,bad2);
        Song song23 = new Song("Goodbye Blue Sky",1979,bad2);
        bad2.getSongs().add(song21); bad2.getSongs().add(song22); bad2.getSongs().add(song23);
        Album album2 = new Album("The Wall",1979);
        album2.getSongs().add(song21); album2.getSongs().add(song22); album2.getSongs().add(song23);
        bad2.getAlbums().add(album2);
        bands.add(bad2);

        //Czwarty zespol
        Band bad3 = new Band();

        //Piaty zespol
        Band bad4 = new Band();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.writeValue(new File("result." +filesuffix),bands);
    }


    public void pobierzZPliku(ObjectMapper mapper, String fileSuffix) throws Exception{
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        InputStream bandIs2 = MyMObjectsCreator.class.getClassLoader().
                getResourceAsStream("musician." + fileSuffix);
        bands = mapper.readValue(bandIs2,new TypeReference<List<Band>>(){});

    }

    public MyMObjectsCreator() {
        albums = new ArrayList<>();
        bands = new ArrayList<>();
        songs = new ArrayList<>();
    }



}
