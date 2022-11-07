//Write a program that gets the first name of all employees
// who have salary over 50 000.

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _4_EmployeesWithSalaryOver50000 {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String EMPLOYEES_WITH_SALARY_OVER_50000 = "SELECT e.firstName FROM Employee e WHERE e.salary > 50000";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.createQuery(EMPLOYEES_WITH_SALARY_OVER_50000, String.class)
                .getResultList()
                .forEach(System.out::println);

        entityManager.close();
    }
}
