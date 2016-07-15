package test.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import po.Person;
public class TestPerson {

private PersonDao personDao=new PersonDaoImpl();
	@Test
	public void testAddDepart() {
		Person person=new Person("abc","19950603","13726259726",1,100000);
		int m =personDao.addPerson(person);
		Assert.assertTrue(m==1);
	}
	@Test
	public void testDeleteDepart() {
		int m=personDao.deletePerson(41);
		Assert.assertTrue(m==1);
	}
	@Test
	public void testUpdateDepart() {
		Person person=personDao.loadPerson(7);
		person.setCname("Tom");
		int m=personDao.updatePerson(person);
		Assert.assertTrue(m==1); 
	}
	@Test
	public void testLoadDepart() {
		Person person=personDao.loadPerson(2);
		Person person1=personDao.loadPerson(4);
		Assert.assertTrue(person!=null);
		Assert.assertTrue(person1!=null);
	}
	@Test
	public void testShowAllDepart() {
		List<Person> ps=personDao.showAllPerson();
		Assert.assertTrue(!ps.isEmpty());
	}

}
