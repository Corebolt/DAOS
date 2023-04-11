import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import daos.CustomerDao;
import entities.Customer;
import entities.Database;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
        //create a list for the records to go into
        List<Customer> customerList;

        //connect to the database and run created functions 
        try (Connection connection = Database.getDatabaseConnection();
        Statement statement = connection.createStatement();) {
            CustomerDao customerDao = new CustomerDao(connection);

            //list all records within the database
            customerList = customerDao.findAll();
            System.out.println("Printing customers: ");
            for (Customer customer : customerList) {
                System.out.println(customer);
            }

        //insert and new customer into the database
        Customer insertCustomer = new Customer();
        insertCustomer.setName("Commander Shepard");
        insertCustomer.setAddress("123 Citadel Ave");
        insertCustomer.setCity("Citadel");
        insertCustomer.setState("MW");
        insertCustomer.setZip("95134");
        customerDao.insert(insertCustomer);

        //find a customer within the database by id
        Customer customer = customerDao.findById(insertCustomer.getId());
        System.out.println("Customer returned from findByID: " + customer);

        //update info in database for a customer
        Customer updateCustomer = new Customer();
        updateCustomer = customerDao.findById(insertCustomer.getId());
        updateCustomer.setAddress("123 London Ln");
        Boolean success = customerDao.update(updateCustomer);
        System.out.println("Customer after address update: " + success);

        // //delete a user from the database
        Boolean succeeded = customerDao.delete(insertCustomer.getId());
        System.out.println(succeeded);

        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
        }
    }
}
