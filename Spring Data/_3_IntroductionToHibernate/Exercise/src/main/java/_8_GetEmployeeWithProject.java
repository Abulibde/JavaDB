/*
Get an employee by his/her id.
Print only his/her first name, last name, job title and projects (only their names).
The projects should be ordered by name (ascending).
The output should be printed in the format given in the example.

                            Example
Input	                        Output
147	                            Linda Randall - Production Technician
	                            HL Touring Handlebars
	                            ML Road Rear Wheel
	                            Patch kit
	                            Touring-1000


83	                            John Evans - Production Technician
	                            Half-Finger Gloves
	                            LL Mountain Handlebars
	                            Racing Socks
	                            Women's Tights

 */

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _8_GetEmployeeWithProject {
    public static final String DATABASE_NAME = "soft_uni";
    public static final String GET_EMPLOYEE_BY_ID = "SELECT e FROM Employee e WHERE e.id = :id";
    public static void main(String[] args) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        int employeeId = new Scanner(System.in).nextInt();

        System.out.println(entityManager.createQuery(GET_EMPLOYEE_BY_ID, Employee.class)
                .setParameter("id",employeeId)
                .getSingleResult()
                .toString());
        
    }
}
