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

    @Column(name = "rokWydania",nullable = false,length = 4)
    private int rokWydania;

    @JsonProperty("idwykonawcy")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idwykonawcy",referencedColumnName = "id")
    private Band band;

    public Song() {
    }

    public Song(String nazwa, int rokWydania,Band bad) {
        this.nazwa = nazwa;
        this.rokWydania = rokWydania;
        this.band = bad;
    }

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

    public int getRokWydania() {
        return rokWydania;
    }

    public void setRokWydania(int rokWydania) {
        this.rokWydania = rokWydania;
    }
}
