package oocl.server.dao;

import oocl.po.Depart;
import oocl.po.Person;

public interface AddDao {

	public int addDepart(Depart depart);
	public int addPerson(Person person);
}
