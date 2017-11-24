package hibernate.klasy;

import com.fasterxml.jackson.annotation.*;

import javax.naming.Name;
import javax.persistence.*;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refIdSong", scope=Song.class)
@Entity
@Table(name = "SONGS")
public class Song {

    @Id @GeneratedValue
    @Column(name = "idUtworu")
    private int idUtwory;

    @Column(name = "nazwa",nullable = false,unique = true)
    private String nazwa;

    //Dlugosc utwory w sekundach
    @JsonProperty("dlugosc")
    @Column(name = "dlugosc",nullable = false)
    private long dlugoscWSekundach;

    @JsonProperty("idwykonawcy")
    @ManyToOne(cascade = CascadeType.ALL)
    private Band band;

    @JsonProperty("idalbumu")
    @ManyToOne(cascade = CascadeType.ALL)
    private Album album;


    public Song() {
    }

    public Song(String nazwa, long dlugosc,Band bad,Album album) {
        this.nazwa = nazwa;
        this.dlugoscWSekundach = dlugosc;
        this.band = bad;
        this.album = album;
    }

    public int getIdUtwory() { return idUtwory; }

    public void setIdUtwory(int idUtwory) { this.idUtwory = idUtwory; }

    public long getDlugosc() { return dlugoscWSekundach; }

    public void setDlugosc(long dlugosc) { this.dlugoscWSekundach = dlugosc; }

    public Album getAlbum() { return album; }

    public void setAlbum(Album album) { this.album = album; }

    public Band getBand() { return band; }

    public void setBand(Band band) { this.band = band; }

    public int getIdSong() { return idUtwory; }

    public void setIdSong(int idUtwory) {
        this.idUtwory = idUtwory;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

}
