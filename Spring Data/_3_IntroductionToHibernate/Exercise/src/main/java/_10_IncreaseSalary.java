/*
Write a program that increases the salaries of all employees,
who are in the Engineering, Tool Design, Marketing or Information Services departments by 12%.
Then print the first name, the last name and the salary for the employees, whose salary was increased.
 */


import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class _10_IncreaseSalary {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String INCREASE_SALARY_IN_DEPARTMENTS =
            "UPDATE Employee e SET e.salary = e.salary * 1.12 " +
                    "WHERE e.department.id IN (1,2,11,4)";
    private static final String GET_EMPLOYEES_FROM_DEPARTMENTS = "SELECT e FROM Employee e " +
            "WHERE e.department.id IN(1,2,11,4)";
    private static final String PRINT_FORMAT_EMPLOYEES = "%s %s ($%.2f)%n";

    public static void main(String[] args) {
        final EntityManager entityManager = Persistence
                .createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(INCREASE_SALARY_IN_DEPARTMENTS).executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.createQuery(GET_EMPLOYEES_FROM_DEPARTMENTS, Employee.class)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORMAT_EMPLOYEES,
                        e.getFirstName(),
                        e.getLastName(),
                        e.getSalary()));

        entityManager.close();
    }
}
