package oocl.server.dao;

import oocl.po.Depart;
import oocl.po.Person;

public interface UpdateDao {

	public int updateDepart(Depart depart);
	public int  updatePerson(Person person);

	public Depart loadDepart(int id);
	public Person loadPerson(int id);
}
