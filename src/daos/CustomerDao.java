package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;

public class CustomerDao implements Dao<Customer, Integer>{
    Connection connection;

    //connect to database
    public CustomerDao(Connection connection){
        this.connection = connection;
    }

    //list all records
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<Customer>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery("SELECT * FROM customer");
            while (result.next()){
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setAddress(result.getString ("address"));
                customer.setCity(result.getString("city"));
                customer.setState(result.getString("state"));
                customer.setZip(result.getString("zip"));
                customers.add(customer);
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return customers;
    }

    //find a record by id
    @Override
    public Customer findById(Integer pk) {
        Customer customer = new Customer();
        String select = "SELECT * FROM customer WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(select);) {
            ps.setInt(1, pk);
            ResultSet result = ps.executeQuery();
            if(result.next())
            {
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setAddress(result.getString ("address"));
                customer.setCity(result.getString("city"));
                customer.setState(result.getString("state"));
                customer.setZip(result.getString("zip"));
                
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return customer;        
    }

    //insert a new record into the database
    @Override
    public void insert(Customer customer) {
        try (Statement statement = connection.createStatement()){
            String insert = "INSERT INTO customer VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, null);
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getState());
            ps.setString(6, customer.getZip());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next())
            {
                customer.setId(keys.getInt(1));
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        
    }

    //update an existing record with new information
    @Override
    public Boolean update(Customer customer) {
        Boolean success = true;
        String update = "UPDATE customer SET address=? WHERE id=?";
        try(PreparedStatement ps = connection.prepareStatement(update);)
        {
            ps.setString(1, customer.getAddress());
            ps.setInt(2, customer.getId());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            success = false;
        }
        return success;
    }

    //delete a current record from the database
    @Override
    public Boolean delete(Integer pk) {
        Boolean success = false;
        String delete = "DELETE FROM customer WHERE id=?";
        try(PreparedStatement ps = connection.prepareStatement(delete))
        {
            ps.setInt(1, pk);

            if(ps.executeUpdate() != 0)
            {
                success = true;
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return success;
    }
    
}
