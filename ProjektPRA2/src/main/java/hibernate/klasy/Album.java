package hibernate.klasy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refIdAlbum", scope=Album.class)
@Entity
@Table(name = "ALBUMS")
public class Album {

    @Id @GeneratedValue
    @Column(name = "idAlbumu")
    private int idAlbumu;

    @Column(name = "nazwa", unique = true)
    private String nazwaAlbumu;

    @Column(name = "rok_wyd")
    private int rokwydania;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Song> songs = new ArrayList<Song>();

    public Album() { }

    public Album(String nazwaAlbumu, int rokwydania) {
        this.nazwaAlbumu = nazwaAlbumu;
        this.rokwydania = rokwydania;
    }

    public List<Song> getSongs() { return songs; }

    public int getIdAlbumu() { return idAlbumu; }

    public void setIdAlbumu(int idAlbumu) { this.idAlbumu = idAlbumu; }

    public String getNazwaAlbumu() { return nazwaAlbumu; }

    public void setNazwaAlbumu(String nazwaAlbumu) { this.nazwaAlbumu = nazwaAlbumu; }

    public int getRokwydania() { return rokwydania; }

    public void setRokwydania(int rokwydania) { this.rokwydania = rokwydania; }
}
