package lt.bit.java.entities;

import com.sun.tools.javac.code.Lint;
import lt.bit.java.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Studentas> studentai = new ArrayList<>();
        uzkrautiDuomenis(studentai);
        kursoVidurkis(2017);

    }

    static void uzkrautiDuomenis(List<Studentas> studentai) {

        EntityManager em = HibernateUtils.getEntityManager();
        em.getTransaction().begin();
        TypedQuery<Studentas> query = em.createQuery("SELECT s FROM Studentas s",Studentas.class)
                .setHint(HibernateUtils.GRAPH_PROPERTY,
                        em.createEntityGraph(Studentas.GRAPH_PAZYMYS));
        studentai = query.getResultList();

//        EntityGraph graph = em.createEntityGraph(Studentas.GRAPH_PAZYMYS);
//        Map<String, Object> hints = new HashMap<>();
//        hints.put(HibernateUtils.GRAPH_PROPERTY, graph);

//        Studentas stud1 = em.find(Studentas.class, 1, hints); //po primary key, nepamirsi prirasyti hints
//        Studentas stud2 = em.find(Studentas.class, 2, hints);
//        Studentas stud3 = em.find(Studentas.class, 3, hints);
//        Studentas stud4 = em.find(Studentas.class, 4, hints);

//        studentai.add(stud1);
//        studentai.add(stud2);
//        studentai.add(stud3);
//        studentai.add(stud4);
        em.getTransaction().commit();
        em.close();
        printStudents(studentai);
    }

    private static void printStudents(List<Studentas> studentai) {
//        studentai.forEach(s -> {
//            System.out.println(s.getVardas() + " " + s.getPavarde() + " " + s.getEl_pastas());
//            s.getPazymiai().forEach(paz -> {
//                System.out.println(paz.getPazymys());
//            });
//        });
        List<Studentas>SortedStudentas = studentai.stream()
                .sorted(Comparator.comparing(Studentas::getPavarde).thenComparing(Studentas::getVardas))
                .collect(Collectors.toList());
        SortedStudentas.forEach(stud -> {
            System.out.println(stud.getVardas() + " " + stud.getPavarde() + " " + stud.getEl_pastas());
            stud.getPazymiai().forEach(pazymys -> {
                System.out.println(pazymys.getPazymys());
            });
        });
    }
      static int kursoVidurkis(int metai) {
        EntityManager em = HibernateUtils.getEntityManager();
        em.getTransaction().begin();
//where p.data = :data).setParameter("data", new Date()); int to date
          TypedQuery<Pazymys> query = em.createQuery("SELECT p FROM Pazymys p", Pazymys.class);
           List<Pazymys> vidurkis = query.getResultList();

//        System.out.println(vidurkis);
          int result = (int) vidurkis.stream()
                  .filter(paz -> paz.getDate().getYear() == metai)
                  .mapToInt(Pazymys::getPazymys)
                  .average()
                  .orElse(Double.NaN);
          System.out.println("Bendras vidurkis " + metai + " metais yra " + result);
          return result;

      }
}
