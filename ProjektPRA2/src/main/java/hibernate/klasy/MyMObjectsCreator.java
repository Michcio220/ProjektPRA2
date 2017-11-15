package hibernate.klasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMObjectsCreator {

    Band bad;
    Band bad2;
    Utwory utw;

    List<Band> bands;

    public Band getBand() { return bad; }

    public void setBand(Band muz) { this.bad = muz; }

    public Utwory getUtw() { return utw; }

    public void setUtw(Utwory utw) { this.utw = utw; }

    public List<Band> getBands() { return bands; }

    public void setMusicians(List<Band> musicians) { this.bands = musicians; }

    public void init(){

        //setup one band
        bad = new Band();
        bad.setNazwa("Pink Floyd");
        bad.setBandId(new Random().nextInt(100));
        bad.setLiczba(4);
        bad.setRok(1977);
        bad.setTyp("Rock");

        //setup second band
        bad2 = new Band();
        bad2.setNazwa("System of Down");
        bad2.setBandId(new Random().nextInt(100));
        bad2.setLiczba(4);
        bad2.setRok(1984);
        bad2.setTyp("Rock");

        Utwory utwor = new Utwory();
        utwor.setNazwa("Another Brick In The Wall");
        utwor.setRokWydania(1982);
        utwor.setIdUtwory(new Random().nextInt(100));

        bad.setUtwor(utwor);

        bands = new ArrayList<Band>();
        bands.add(bad);
        bands.add(bad2);




    }

    public MyMObjectsCreator() { init(); }


}
