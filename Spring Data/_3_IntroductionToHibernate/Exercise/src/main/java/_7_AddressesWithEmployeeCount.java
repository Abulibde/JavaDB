/*
Find all addresses, ordered by the number of employees who live there (descending).
Take only the first 10 addresses and print their address text, town name and employee count.
 */

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _7_AddressesWithEmployeeCount {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String ALL_ADDRESSES_ORDERED_BY_NUBER_OF_EMPLOYEES_DESC =
            "SELECT a FROM Address a ORDER BY a.employees.size DESC";
    public static void main(String[] args) {

        EntityManager entityManager = Persistence
                .createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        entityManager.createQuery(ALL_ADDRESSES_ORDERED_BY_NUBER_OF_EMPLOYEES_DESC, Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);

        entityManager.close();
    }
}
