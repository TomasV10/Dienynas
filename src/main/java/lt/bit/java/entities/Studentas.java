package lt.bit.java.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "studentai")
@NamedEntityGraph(
        name = Studentas.GRAPH_PAZYMYS,
        attributeNodes = @NamedAttributeNode("pazymiai")
)


@NamedQuery(
        name = Studentas.QUERY_ALL,
        query = "SELECT s FROM Studentas s"
)

public class Studentas {
    public static final String GRAPH_PAZYMYS = "graph.driver.pazymiai";
    public static final String QUERY_ALL = "query.driver.all";
    public static final String QUERY_BY_NAME = "query.driver.byName";
    public static final String QUERY_AVG = "query.driver.avg";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vardas")
    private String vardas;
    @Column(name = "pavarde")
    private String pavarde;
    @Column(name = "el_pastas")
    private String el_pastas;

    @OneToMany(mappedBy = "studentas",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Pazymys> pazymiai = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public String getEl_pastas() {
        return el_pastas;
    }

    public void setEl_pastas(String el_pastas) {
        this.el_pastas = el_pastas;
    }

    public List<Pazymys> getPazymiai() {
        return pazymiai;
    }

    public void setPazymiai(List<Pazymys> pazymiai) {
        this.pazymiai = pazymiai;
    }

    public void addPazymys(Pazymys pazymys){
        pazymiai.add(pazymys);
        pazymys.setStudentas(this);
    }

    @Override
    public String toString() {
        return "Studentas{" +
                "id=" + id +
                ", vardas='" + vardas + '\'' +
                ", pavarde='" + pavarde + '\'' +
                ", el_pastas='" + el_pastas + '\'' +
                '}';
    }
}
