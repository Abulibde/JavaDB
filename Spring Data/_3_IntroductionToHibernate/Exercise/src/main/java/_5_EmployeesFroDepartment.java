//Extract all employees from the Research and Development department.
// Order them by salary (in ascending order), then by id (in ascending order).
// Print only their first name, last name, department name and salary.


import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _5_EmployeesFroDepartment {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String DEPARTMENT_NAME = "Research and Development";
    private static final String SELECT_EMPLOYEES_WITH_DEPARTMENT_ORDERED_BY_SALARY_AND_ID =
            "SELECT e FROM Employee e WHERE e.department.name=:dp order by e.salary asc, e.id asc";

    private static final String PRINT_FORMAT = "%s %s from %s - $%.2f%n";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager
                .createQuery(SELECT_EMPLOYEES_WITH_DEPARTMENT_ORDERED_BY_SALARY_AND_ID, Employee.class)
                .setParameter("dp", DEPARTMENT_NAME)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORMAT,
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getName(),
                        e.getSalary()));

        entityManager.close();
    }
}
