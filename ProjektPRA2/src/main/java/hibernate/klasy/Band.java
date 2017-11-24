package hibernate.klasy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="refId", scope=Band.class)
@Entity
@Table(name = "MUSICIANS")
public class Band {

    @Id @GeneratedValue
    @Column(name= "id")
    private int bandId;

    @Column(name = "nazwazespolu",unique = true,nullable = false)
    private String nazwa;

    @Column(name = "rokZalozenia",nullable = false)
    private int rok;

    @Column(name = "liczbaCzlonkow",nullable = false)
    private int liczba;

    @Column(name = "typMuzyki",nullable = false)
    private String typ;

    @OneToMany(mappedBy = "band",cascade = CascadeType.ALL)
    private List<Album> albums = new ArrayList<Album>();

    public Band() {
    }

    public Band(String nazwa, int rok, int liczba, String typ) {
        this.nazwa = nazwa;
        this.rok = rok;
        this.liczba = liczba;
        this.typ = typ;
    }

    public void setAlbums(List<Album> albums) { this.albums = albums; }

    public List<Album> getAlbums() { return albums; }

    public int getBandId() { return bandId; }

    public String getNazwa() { return nazwa; }

    public void setNazwa(String nazwa) { this.nazwa = nazwa; }

    public int getRok() { return rok; }

    public void setRok(int rok) { this.rok = rok; }

    public int getLiczba() { return liczba; }

    public void setLiczba(int liczba) { this.liczba = liczba; }

    public String getTyp() { return typ; }

    public void setTyp(String typ) { this.typ = typ; }

}
