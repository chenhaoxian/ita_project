package dao;

import java.util.List;

import po.Customer;

public interface CustomerDao {
public int addCustomer(Customer customer);
public int deleteCustomer(int id);
public int  updateCustomer(Customer customer);
public List<Customer> showAllCustomers();
public Customer loadCustomer(int id);
}
