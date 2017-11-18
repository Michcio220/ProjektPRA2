package hibernate;

import hibernate.klasy.Album;
import hibernate.klasy.Band;
import hibernate.klasy.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMObjectsCreator {

    List<Band> bands;
    List<Song> songs;
    List<Album> albums;

    public List<Band> getBands() { return bands; }

    public List<Song> getSongs() { return songs; }

    public List<Album> getAlbums() { return albums; }

    public void zapiszDoPliku(){

        Band bad1 = new Band();
        bad1.setNazwa("The Beatles");
        bad1.setLiczba(4);
        bad1.setTyp("Rock");
        bad1.setRok(1960);
        bad1.setBandId(new Random().nextInt());

        Band bad2 = new Band();
        bad2.setNazwa("System of Down");
        bad2.setLiczba(4);
        bad2.setTyp("Hard Rock");
        bad2.setRok(1994);
        bad2.setBandId(new Random().nextInt());

        Album alb = new Album();
        alb.setRokwydania(1969);
        alb.setNazwaAlbumu("Abbey Road");
        bad1.getAlbums().add(alb);

        Song sog = new Song();
        sog.setRokWydania(1969);
        sog.setNazwa("Something");
        sog.setBand(bad1);
        sog.setWykonawca();
        bad1.getSongs().add(sog);
        alb.getSongs().add(sog);

        bands = new ArrayList<Band>();
        bands.add(bad1); bands.add(bad2);

    }

    public MyMObjectsCreator() { zapiszDoPliku(); }



}
