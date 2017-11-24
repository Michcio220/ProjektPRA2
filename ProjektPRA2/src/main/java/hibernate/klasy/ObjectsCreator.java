package hibernate.klasy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//Klasa ktora pomaga tworzyc pliki danych json/xml
public class ObjectsCreator {

    List<Band> bands;
    List<Song> songs;
    List<Album> albums;

    public List<Band> getBands() { return bands; }

    public List<Song> getSongs() { return songs; }

    public List<Album> getAlbums() { return albums; }

    //Stworzenie pliku json/xml z danymi
    public void  zapiszDoPliku(ObjectMapper mapper , String filesuffix) throws IOException {

        //Pierwszy zespol
        Band bad = new Band("FAKE TYPE",2013,2,"HipHop");
        Album album = new Album("FAKE BOOK",2013,bad);
        Song song = new Song("Devil's Wedding",281,bad,album);
        Song song1 = new Song("Bacchus",272,bad,album);
        Song song2 =  new Song("MONSTER'S ZOO",262,bad,album);
        album.getSongs().add(song);album.getSongs().add(song1);album.getSongs().add(song2);
        bad.getAlbums().add(album);
        bands.add(bad);

        //Drugi zespol
        Band bad1 = new Band("The Beatles",1960,4,"Rock");
        Album album1 = new Album("Let It Be",1970,bad1);
        Song song11 = new Song("Two Of Us",216,bad1,album1);
        Song song12 = new Song("Dig A Pony",235,bad1,album1);
        Song song13 = new Song("Across The Universe",228,bad1,album1);
        album1.getSongs().add(song11); album1.getSongs().add(song12); album1.getSongs().add(song13);
        bad1.getAlbums().add(album1);
        bands.add(bad1);

        //Trzeci zespol
        Band bad2 = new Band("Pink Floyd",1965,5," Progressive Rock");
        Album album2 = new Album("The Wall",1979,bad2);
        Song song21 = new Song("The Thin Ice",149,bad2,album2);
        Song song22 = new Song("Another Brick In The Wall,Part 1",192,bad2,album2);
        Song song23 = new Song("Goodbye Blue Sky",167,bad2,album2);
        album2.getSongs().add(song21); album2.getSongs().add(song22); album2.getSongs().add(song23);
        bad2.getAlbums().add(album2);
        bands.add(bad2);

        //Czwarty zespol
        Band bad3 = new Band("System Of Down",1994,5,"Hard Rock");
        Album album3 = new Album("Hypnotize",2005,bad3);
        Song song31 = new Song("Attack",186,bad3,album3);
        Song song32 = new Song("Dreaming",239,bad3,album3);
        Song song33 = new Song("Kill Rock'n Roll",147,bad3,album3);
        album3.getSongs().add(song31); album3.getSongs().add(song32); album3.getSongs().add(song33);
        bad3.getAlbums().add(album3);
        bands.add(bad3);

        //Piaty zespol
        Band bad4 = new Band("Led Zeppelin",1968,4,"Blues Rock");
        Album album4 = new Album("Celebration Day",2012,bad4);
        Song song41 = new Song("Good Times Bad Times",166,bad4,album4);
        Song song42 = new Song("Ramble On",263,bad4,album4);
        Song song43 = new Song("Black Dog",295,bad4,album4);
        album4.getSongs().add(song41); album4.getSongs().add(song42); album4.getSongs().add(song43);
        bad4.getAlbums().add(album4);
        bands.add(bad4);

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.writeValue(new File("result." +filesuffix),bands);
    }

    //Funkcja do poberania dancych z plik json/xml
    public void pobierzZPliku(ObjectMapper mapper, String fileSuffix) throws Exception{
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        InputStream bandIs2 = ObjectsCreator.class.getClassLoader().
                getResourceAsStream("musician." + fileSuffix);
        bands = mapper.readValue(bandIs2,new TypeReference<List<Band>>(){});

    }

    public ObjectsCreator() {
        albums = new ArrayList<>();
        bands = new ArrayList<>();
        songs = new ArrayList<>();
    }

    public static void main(String [] args) throws IOException {

        ObjectMapper xmlmapper = new XmlMapper();
        ObjectsCreator objectsCreator = new ObjectsCreator();
       // objectsCreator.zapiszDoPliku(xmlmapper,"xml");
        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectsCreator objectsCreator1 = new ObjectsCreator();
       // objectsCreator1.zapiszDoPliku(jsonMapper,"json");
    }



}
