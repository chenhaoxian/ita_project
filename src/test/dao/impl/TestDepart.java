package test.dao.impl;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import po.Depart;
import dao.DepartDao;
import dao.impl.DepartDaoImpl;
public class TestDepart {

private DepartDao departDao=new DepartDaoImpl();
	@Test
	public void testAddDepart() {
		Depart depart=new Depart("sun", "zha");
		int m =departDao.addDepart(depart);
		Assert.assertTrue(m==1);
	}
	@Test
	public void testDeleteCustomer() {
		int m=departDao.deleteDepart(4);
		Assert.assertTrue(m==1);
	}

}
