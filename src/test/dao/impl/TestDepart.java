package test.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

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
//	@Test
	public void testDeleteDepart() {
		int m=departDao.deleteDepart(4);
		Assert.assertTrue(m==1);
	}
	@Test
	public void testUpdateDepart() {
		Depart depart=departDao.loadDepart(7);
		depart.setDname("TomLi");
		int m=departDao.updateDepart(depart);
		Assert.assertTrue(m==1);
	}
	@Test
	public void testLoadDepart() {
		Depart depart=departDao.loadDepart(6);
		Depart depart1=departDao.loadDepart(7);
		Assert.assertTrue(depart!=null);
		Assert.assertTrue(depart1!=null);
	}
	@Test
	public void testShowAllDepart() {
		List<Depart> ds=departDao.showAllDepart();
		Assert.assertTrue(!ds.isEmpty());
	}

}
