import entities.Town;
//2.	Change casing
// Use the soft_uni database. Persist all towns from the database.
// Detach those whose name length is more than 5 symbols.
// Then transform the names of all attached towns to uppercase and save them to the database.



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class _2_ChangeCasing {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String UPDATE_ALL_TOWNS_WITH_LENGTH_MORE_THAN_5 =
            "UPDATE Town t SET t.name = UPPER(t.name) WHERE length(t.name) <= 5";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query towns = entityManager.createQuery(UPDATE_ALL_TOWNS_WITH_LENGTH_MORE_THAN_5);
        towns.executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();



    }
}
