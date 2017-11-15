package hibernate.klasy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="refId", scope=Band.class)
@Entity
@Table(name = "MUSICIANS")
public class Band {

    public Band() {
    }

    public Band( String nazwa, int rok, int liczba, String typ) {
        this.nazwa = nazwa;
        this.rok = rok;
        this.liczba = liczba;
        this.typ = typ;
    }

    @Id @GeneratedValue
    @Column(name= "id")
    private int bandId;

    @Column(name = "nazwazespolu")
    private String nazwa;

    @Column(name = "rokZalozenia")
    private int rok;

    @Column(name = "liczbaCzlonkow")
    private int liczba;

    @Column(name = "typMuzyki")
    private String typ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Utwory_id",referencedColumnName = "idUtworu")
    Utwory utwor;


    @ManyToMany(mappedBy = "utwory",cascade = CascadeType.ALL)
    private List<Utwory> utwory = new ArrayList<>();

    public void addUtwor(Utwory utwor){ utwory.add(utwor);}

    public int getBandId() { return bandId; }

    public void setBandId(int bandId) { this.bandId = bandId; }

    public String getNazwa() { return nazwa; }

    public void setNazwa(String nazwa) { this.nazwa = nazwa; }

    public int getRok() { return rok; }

    public void setRok(int rok) { this.rok = rok; }

    public int getLiczba() { return liczba; }

    public void setLiczba(int liczba) { this.liczba = liczba; }

    public Utwory getUtwor() { return utwor; }

    public void setUtwor(Utwory utwor) { this.utwor = utwor; }

    public String getTyp() { return typ; }

    public void setTyp(String typ) { this.typ = typ; }

}
