package test.dao.impl;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import dao.CustomerDao;
import dao.impl.CustomerDaolmpl;
import po.Customer;

public class TestCustomerDaolmplTest {
private CustomerDao cd=new CustomerDaolmpl();
	@Test
	public void testAddCustomer() {
		Customer customer=new Customer("sun", 99);
		int m =cd.addCustomer(customer);
		Assert.assertTrue(m==1);
	}

	@Test
	public void testDeleteCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCustomer() {
		SUCCESSFUL("Not yet implemented");
	}

	private void SUCCESSFUL(String string) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testShowAllCustomers() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadCustomer() {
		fail("Not yet implemented");
	}

}
