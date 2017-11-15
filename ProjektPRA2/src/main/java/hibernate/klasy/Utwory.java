package hibernate.klasy;

import javax.persistence.*;

@Entity
@Table(name = "SONGS")
public class Utwory {

    public Utwory() {
    }

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "performer_seq")
    @Column(name = "idUtworu")
    private int idUtwory;

    @Column(name = "nazwa",nullable = false)
    private String nazwa;

    @Column(name = "rokWydania",nullable = false,length = 4)
    private int rokWydania;

    public Utwory(String nazwa, int rokWydania) {
        this.nazwa = nazwa;
        this.rokWydania = rokWydania;
    }

    public int getIdUtwory() { return idUtwory; }

    public void setIdUtwory(int idUtwory) {
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
