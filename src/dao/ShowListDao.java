package dao;

import java.util.List;

import po.Depart;
import po.Person;

public interface ShowListDao {


	public List<Depart> showAllDepart();
	public List<Person> showAllPerson();
	public List<Person> findPersonByDid(int id);
}
