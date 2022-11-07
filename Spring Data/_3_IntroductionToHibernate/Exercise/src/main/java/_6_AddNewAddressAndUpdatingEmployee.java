// Create a new address with text "Vitoshka 15".
// Set that address to an employee with a last name, given as an input.

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _6_AddNewAddressAndUpdatingEmployee {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String NEW_ADDRESS = "Vitoshka 15";
    private static final String SET_NEW_ADDRESS_TO_EMPLOYEE_WITH_LAST_NAME =
            "UPDATE Employee e set e.address = :newAddress WHERE e.lastName=:ln";

    public static void main(String[] args) {
        final EntityManager entityManager = Persistence
                .createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        Address newAddress = new Address();
        newAddress.setText(NEW_ADDRESS);

        String lastName = new Scanner(System.in).nextLine();

        entityManager.getTransaction().begin();
        entityManager.persist(newAddress);

        int countOfChanges = entityManager.createQuery(SET_NEW_ADDRESS_TO_EMPLOYEE_WITH_LAST_NAME)
                .setParameter("newAddress", newAddress)
                .setParameter("ln", lastName)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("number of updated addresses: " + countOfChanges);
    }
}
