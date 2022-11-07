//Use the soft_uni database. Write a program that checks
// if a given employee name is contained in the database.
//                      Example
//      Input	                    Output
//      Svetlin Nakov	            Yes
//      John Doe	                No


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _3_ContainsEmployee {
    private static final String DATABASE_NAME = "soft_uni";

    private static final String FIND_NAME = "SELECT count(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        String[] fullName = new Scanner(System.in).nextLine().split(" ");
        String firstName = fullName[0];
        String lastName = fullName[1];

        Long countOfMatches = entityManager
                .createQuery(FIND_NAME, Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();

        if (countOfMatches == 0) {
            System.out.println("NO");
        }else {
            System.out.println("Yes");
        }
    }
}
