package dao;

import po.Depart;
import po.Person;

public interface UpdateDao {

	public int updateDepart(Depart depart);
	public int  updatePerson(Person person);

	public Depart loadDepart(int id);
	public Person loadPerson(int id);
}
