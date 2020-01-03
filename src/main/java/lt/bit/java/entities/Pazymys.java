package lt.bit.java.entities;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pazymiai")

@NamedQuery(
        name = Studentas.QUERY_AVG,
        query = "SELECT p FROM Pazymys p"
)

public class Pazymys {
    private Integer id;
    private LocalDateTime date;
    private int pazymys;
    private Studentas studentas;



    @Column(name = "pazymys")
    public int getPazymys() {
        return pazymys;
    }

    public void setPazymys(int pazymys) {
        this.pazymys = pazymys;
    }

    public void setStudentas(Studentas studentas) {
        this.studentas = studentas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "data")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    @ManyToOne (fetch = FetchType.EAGER)
    public Studentas getStudentas() {
        return studentas;
    }

    @Override
    public String toString() {
        return "Pazymys{" +
                "id=" + id +
                ", date=" + date +
                ", pazymys=" + pazymys +
                ", studentas=" + studentas +
                '}';
    }
}
