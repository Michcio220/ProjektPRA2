package hibernate.klasy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refIdAlbum", scope=Album.class)
@JsonPropertyOrder({"idAlbumu", "nazwa", "rokwydania", "idwykonawcy", "songs"})
@Entity
@Table(name = "ALBUMS")
public class Album {

    @Id @GeneratedValue
    @Column(name = "idAlbumu")
    private int idAlbumu;

    @Column(name = "nazwa", unique = true,nullable = false)
    private String nazwaAlbumu;

    @Column(name = "rok_wyd",nullable = false)
    private int rokwydania;

    @JsonProperty("idwykonawcy")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idwykonawcy",nullable = false)
    private Band band;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Song> songs = new ArrayList<Song>();

    public Album() { }

    public Album(String nazwaAlbumu, int rokwydania,Band band) {
        this.nazwaAlbumu = nazwaAlbumu;
        this.rokwydania = rokwydania;
        this.band = band;
    }

    public List<Song> getSongs() { return songs; }

    public int getIdAlbumu() { return idAlbumu; }

    public void setIdAlbumu(int idAlbumu) { this.idAlbumu = idAlbumu; }

    public String getNazwaAlbumu() { return nazwaAlbumu; }

    public void setNazwaAlbumu(String nazwaAlbumu) { this.nazwaAlbumu = nazwaAlbumu; }

    public int getRokwydania() { return rokwydania; }

    public void setRokwydania(int rokwydania) { this.rokwydania = rokwydania; }
}
