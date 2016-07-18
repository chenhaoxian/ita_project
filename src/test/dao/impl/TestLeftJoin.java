package test.dao.impl;

import static org.junit.Assert.*;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;

import oocl.po.Person;
import oocl.server.dao.DeleteDao;
import oocl.server.dao.ShowListDao;
import oocl.server.dao.impl.DeleteDaoImpl;
import oocl.server.dao.impl.ShowListDaoImpl;
import oracle.net.aso.s;

public class TestLeftJoin {
	ShowListDaoImpl leftJoin=new ShowListDaoImpl();
	@Test
	public void test() {
		List<Person> ps=leftJoin.findPersonByDid(5);
		for(Person pss:ps){
			System.out.println(pss.getCity());
			System.out.println(pss.getDname());
		}
		Assert.assertTrue(!ps.isEmpty());
	}	
	@Test
	public void testDelete() {
		DeleteDao dd=new DeleteDaoImpl();
		dd.deleteDepart(6);
	}
	@Test
	public void regex() {
		String string="A-P";
		String rString="(A|U|L)-(P|D|PA|PD|PA)";
		Pattern pattern=Pattern.compile(rString,Pattern.CASE_INSENSITIVE);
		java.util.regex.Matcher matcher= pattern.matcher(string);
		System.out.println(matcher.equals(string));
	}

}
