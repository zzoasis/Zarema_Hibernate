package zarema_hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Players;
import org.hibernate.criterion.Restrictions;

public class Zarema_Hibernate {

    public static void main(String[] args) {
        Session roster = connection.Controller.getSessionFactory().openSession();
        Transaction trans = roster.beginTransaction();

        System.out.println("Данные из таблицы:");
        list(roster.createCriteria(pojos.Players.class).list());

        System.out.println("Добавление новых данных в таблицу.");
        //Сохранение данных в таблицу
        roster.save(new pojos.Players("Серж", "25", "Германия", "полузащитник"));
        roster.save(new pojos.Players("Йозуа", "25", "Германия", "полузащитник"));
        roster.save(new pojos.Players("Альфонсо", "19", "Канада", "защитник"));
        roster.save(new pojos.Players("Марк", "23", "Испания", "полузащитник"));
        roster.save(new pojos.Players("Бенжамен", "24", "Франция", "защитник"));
        list(roster.createCriteria(pojos.Players.class).list());

        System.out.println("Удаление записи, где имя игрока - 'Марк'.");
        pojos.Players mark = (pojos.Players) roster.createCriteria(pojos.Players.class)
                .add(Restrictions.eq("name", "Марк"))
                .uniqueResult();
        roster.delete(mark);
        list(roster.createCriteria(pojos.Players.class).list());

        System.out.println("Изменение имени игрока с 'Бенжамен' на 'Бенедикт'");
        pojos.Players benjamin = (pojos.Players) roster.createCriteria(pojos.Players.class)
                .add(Restrictions.eq("name", "Бенжамен"))
                .uniqueResult();
        benjamin.setName("Бенедикт");
        roster.update(benjamin);
        list(roster.createCriteria(pojos.Players.class).list());

        //Изменение данных 3 игрока - Томас
        System.out.println("Изменение данных 3 игрока - Томас");
        pojos.Players player3 = (pojos.Players) roster.load(pojos.Players.class, 3);
        player3.setName("Джамал");
        player3.setAge("17");

        roster.update(player3);
        list(roster.createCriteria(pojos.Players.class).list());


    }

    private static void list(List<Players> names) {
        names.forEach(System.out::println);
        System.out.println("");
    }
}
